import java.util.Arrays;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class WaterDrop {
    
    public static void waterDrop(int position, int amount, int[] terrain) {
        if (position >= terrain.length || position < 0) {
            //Todo printTerrain as is
        }
        if ( terrain[position] == 0 ) {
            //Todo printTerrain as is
        }
        int[] waterPositions = new int[terrain.length];
        waterDrop(position, amount, terrain, waterPositions);
    }

    public static void printOutput(int[] terrain, int[] waterPositions) {
        int maxHeight = java.util.Collections.max(Arrays.asList(terrain)) + java.util.Collections.max(Arrays.asList(waterPositions));
        string[] result = new string[maxHeight];
        int currentStringIndex = 0;
        for (int i = 0; i < maxHeight; i++ ) {
            for (int j = 0; j < terrain.length; j++) {
                if (terrain[j] > 0) {
                    result[i] += "x";
                } else if ( waterPositions[j] > 0) {
                    result[i] += "w";
                }
            }
        }
    }

    public static void waterDrop(int position, int amout, int[] terrain, int[] waterPositions) {
        int prevPosition = position - 1;
        int nextPosition = position + 1;
        boolean validPrevPosition = prevPosition < terrain.legnth && prevPosition >= 0;
        boolean validNextPosition = nextPosition < terrain.legnth && nextPosition >= 0;
        for (int i = 0; i < amount; i++ ) {
            int currentHeight = terrain[position] + waterPositions[position];
            if (validPrevPosition && validNextPosition) {
                int prevHeight = terrain[prevPosition] + waterPositions[prevPosition];
                int nextHeight = terrain[nextPosition] + waterPositions[nextPosition];
                if ( currentHeight < prevHeight && currentHeight < nextHeight ) {
                    waterPositions[position]++;
                } else if ( currentHeight < prevHeight) {
                    waterDrop(nextPosition, 1, terrain, waterPositions);
                } else {
                    waterDrop(prevPosition, 1, terrain, waterPositions);
                }
            } 
            else if (validNextPosition) {
                int nextHeight = terrain[nextPosition] + waterPositions[nextPosition];
                if ( currentHeight < nextHeight ) {
                    //print 
                    return;
                }
                waterDrop(nextPosition, 1, terrain, waterPositions);
            } else if (validPrevPosition) {
                int prevHeight = terrain[prevPosition] + waterPositions[prevPosition];
                if ( currentHeight < prevHeight ) {
                    //print 
                    return;
                }
                waterDrop(prevPosition, 1, terrain, waterPositions);
            } else {
                //print
                return;
            }
        }
    }

}