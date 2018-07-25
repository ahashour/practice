import java.util.function.Function;

class ZooKeeperNode {
    public HashMap<ZooKeeperNode> children;
    public string value;
    public string path;
    public Function<String,String> callback;
    // public Z parent

    public ZooKeeperNode(string path, string value, Function<String, String> callback) {
        this.ZooKeeperNode(path, value);
        this.callback = callback;
    }
    public ZooKeeperNode(string path, string value, Function<String) {
        this.path = path;
        this.value = value;
        this.childer = new HashMap<ZooKeeperNode>();

    }
} 

class ZooKeeper {
    ZooKeeperNode root;
    public ZooKeeper() {
        root = new ZooKeeperNode("/", "");
    }

    private String fixPath(String path) {
        String output = "/";
        int lastChar = 0;
        for (int i = 0; i < path.length(); i++ ) {
            if (path.charAt(i) == '/' && output.charAt(lastChar) == '/') {
                continue;
            } else {
                output += path.charAt(i);
            }
        }
        return output;
    }
    
    private ZooKeeperNode getParent(String path, boolean doCallback = false) {
        path = fixPath(path);
        int currentFolderIndex = path.lastIndexOf("/");
        return getDFS(path.subString(0, currentFolderIndex), doCallback);
    }

    public boolean create(string path, string value) {
        ZooKeeperNode parent = getParent(path);
        if (parent == null) {
            return false;
        }
        if (parent.children.contains(currentFolder)) {
            return false;
        }
        parent.children.put(currentFolder, new ZooKeeperNode(currentFolder, value));
        return true; 
    }

    public boolean set(string path, string value) {
        ZooKeeperNode parent = getParent(path, doCallback);
        if (parent == null) {
            return false;
        }
        if (parent.children.contains(currentFolder)) {
            parent.children.get(currentFolder).value = value;
            //TODO callback with parents 
            return true;    
        }
        return false;
    }

    private ZooKeeperNode getDFS(String path, boolean doCallback = false) {
        path = fixPath(path);
        ZooKeeperNode current = root;
        String[] folderIds = path.split('/');
        for (String id : folderIds) {
            if (current == null) {
                return null;
            }
            if (!current.childern.contains(id)) {
                return null;
            }
            current = current.childern.get(id);
        }
        return current;
    }

    public String get(string path) {
        ZooKeeperNode result = getDFS(path);
        if (result == null) {
            return null;
        }
        return result.value;
    }

    public boolean watch(String path, Function<String, String> callback) {
        ZooKeeperNode currentNode = getDFS(path);
        if (currentNode == null) {
            return false;
        }
        currentNode.callback = callback;
        return true;
    }

}