class linkedList {
  constructor(value) {
    this.head = { value };
    this.size = 1;
  }
  //add(e)
  //insertAt(e,i)
  //removeFrom(i)
  //removeElement(e)
  //isEmpty()
  //print()
  add(value) {
    let current = this.head;
    while (current.next) {
      current = current.next;
    }
    current.next = { value };
    this.size++;
    return this.head;
  }

  nodeAtIndex(index) {
    let current = this.head;
    for (let i = 0; i < index && current.next; i++) {
      current = current.next;
    }
    return current;
  }

  insertAt(value, index) {
    if (index === 0) return (this.head = { value, next: this.head });
    let current = this.nodeAtIndex(index - 1);
    let node = { value, next: current.next };
    current.next = node;
    this.size++;
    return this.head;
  }

  removeFrom(index) {
    if (index >= this.size) return null;
    let current = this.nodeAtIndex(index - 1);
    const result = current.next;
    current.next = current.next.next;
    return result;
  }

  print() {
    let current = this.head;
    let result = "";
    while (current) {
      result += current.value + "->";
      current = current.next;
    }
    return result;
  }
}

const myList = new linkedList(1);
console.log(myList.print());