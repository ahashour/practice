// Queue class
class Queue {
  // Array is used to implement a Queue
  constructor() {
    this.items = new Array(5);
    this.end = 0;
    this.front = 0;
    this.length = 5;
  }

  // Functions to be implemented
  // enqueue(item)
  // dequeue()
  // front()
  // isEmpty()
  // printQueue()
  enqueue(item) {
    // this.items.push(item);
    let index = this.end++;
    if (index < this.length) {
      this.items[index] = item;
      this.end = index;
    } else {
      if (0 !== this.front) {
        this.items[0] = item;
        this.end = 0;
      } else {
        let x = this.items;
        this.items = new Array(this.length * 2);
        this.length *= 2;
        x.forEach((y, i) => (this.items[i] = y));
        this.front = 0;
        this.end = x.length;
        this.items[x.length] = item;
      }
    }
  }

  dequeue = () => {
    let value = this.items[this.front];
    this.front++;
    if (front === this.length) front = 0;
    return value;
  };

  front = () => {
    return this.items[this.front];
  };
}

const que = new Queue();
console.log(que.items);