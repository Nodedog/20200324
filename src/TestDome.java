/*
*                           单      链         表
*
*                 思路一定要严谨                2:19:44
* */



public class TestDome {

    public static void createCut(Node headA,Node headB){
        headA.next = headB.next.next.next;
    }

    //最好写在MylinedList类的外边
    // 9. 输入两个链表，找出它们的第一个公共结点。
    public static Node towNode(Node headA, Node headB) {
        if (headA == null || headB == null){
            return null;
        }
        //定义两个长度
        int lenA = 0;
        int lenB = 0;
        //定义两个节点
        Node pL = headA;
        Node pS = headB;
        while (pL != null){
            pL = pL.next;
            lenA++;
        }
        while (pS != null){
            pS = pS.next;
            lenB++;
        }
        //因为上面 两个循环 导致pL和pS 为null 所以要重新指想 head
        pL = headA;
        pS = headB;
        int len = lenA - lenB;
        if (len < 0){
            pL = headB;
            pS = headA;
            len = lenB -lenA;
        }
        while (len > 0){
            pL = pL.next;
            len--;
        }
        while (pL != pS && pL != null ){
            pL = pL.next;
            pS = pS.next;
        }
        if (pL == pS && pL != null){
            return pL;
        }
        return null;
    }


    //5. 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
    public static Node mergeTwoLists(Node headA, Node headB) {
        Node newHead = new Node(-1);
        Node tmp = newHead;
        //1、当两个单链表都不为空的时候
        while (headA != null && headB != null) {
            if (headA.data < headB.data) {
                tmp.next = headA;
                headA = headA.next;
                tmp = tmp.next;
            } else {
                tmp.next = headB;
                headB = headB.next;
                tmp = tmp.next;
            }
            if (headA != null) {
                tmp.next = headA;
            }
            if (headB != null) {
                tmp.next = headB;
            }

        }
        return newHead.next;
    }

    public static void main(String[] args) {

        MylinedList mylinedList = new MylinedList();
        //尾插
        mylinedList.addLast(1);
        mylinedList.addLast(2);
        mylinedList.addLast(3);
        mylinedList.addLast(4);
        mylinedList.addLast(5);
        mylinedList.display();
        //判断是否有环
        mylinedList.creatLoop();
        System.out.println(mylinedList.hasCycle());
    }


    public static void main1(String[] args) {

        MylinedList mylinedList = new MylinedList();
        //尾插
        mylinedList.addLast(1);
        mylinedList.addLast(2);
        mylinedList.addLast(3);
        mylinedList.addLast(4);
        mylinedList.addLast(5);
        mylinedList.display();
        System.out.println("=================================");
        MylinedList mylinedList2 = new MylinedList();
        mylinedList2.addLast(10);
        mylinedList2.addLast(20);
        mylinedList2.addLast(30);
        mylinedList2.addLast(40);
        mylinedList2.addLast(50);
        mylinedList2.display();
        /*//在index处插入
        System.out.println(mylinedList.addIndex(4,50));
        mylinedList.display();

        //单链表 长度
        System.out.println(mylinedList.size());
        System.out.println("============================");


        //去除第一个关键字 1000
        mylinedList.remove(1000);
        mylinedList.display();
        System.out.println();
        System.out.println("==================");

        //去除所有关键字10
        mylinedList.removeAllKey(10);
        mylinedList.display();

        //翻转单链表
        mylinedList.display2(mylinedList.exchangList());


        //找中间节点
        Node ret = mylinedList.middleNode();
        System.out.println(ret.data);



        //找倒数第k个节点
        Node num = mylinedList.node(4);
        System.out.println(num.data);


        //输入x 重新排序两个链表 一个里面的data比x 小   另一个别x 大 两个单链表拼接出来
        mylinedList.display2(mylinedList.partition(4));


        //删除 有序  链表 重复的 节点
        mylinedList.removeSame();
        mylinedList.display();


       //判断是否是回文结构
        System.out.println(mylinedList.palinDrome());*/


        // 输入两个链表，找出它们的第一个公共结点。
        createCut(mylinedList.head,mylinedList2.head);
        Node ret = towNode(mylinedList.head, mylinedList2.head);
        System.out.println(ret.data);
        mylinedList.display();
   // 2.07.56
    }
}


//节点
class Node{
    public int data;
    public Node next;

    public Node(int data){
        this.data = data;
        this.next = null;//构造方法里面没有传入Node next的参数是因为 =null 所以可以不用传
    }
}



//单链表
class MylinedList {
    public Node head;
    public MylinedList(){
        this.head = null;
    }


    //头插法   时间复杂度 O（1）
    public void addFirst(int data){
        Node node = new Node(data);//根据class Node 定义一个新的node对象，class Node
                                    //里面的构造方法 有一个参数 data 所以定义时 括号里面带上
        if (this.head == null){//如果是第一次插入
            this.head = node;
            return;
        }
        node.next = this.head;//将head保存的地址给了 新定义的node的next存放
        this.head = node;//将node定义为 头 head
    }



    //打印单链表
    public void display(){
        Node cur = this.head;
        while(cur != null){
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
    }


    //尾插法  时间复杂度 是 O(n)
    public void addLast(int data){
        Node node = new Node(data);
        if (this.head == null){
            this.head = node;
            return;
        }
        Node cur = this.head;
        while (cur.next != null){
          cur = cur.next;
        }
        cur.next = node;
    }



    //任意位置插入,第一个数据节点为0号下标
    public boolean addIndex(int index,int data){
        Node node = new Node(data);
        //思路1：先判断index 取值是否合法
        if (index < 0 ||index > size() ){
            return false;
        }
        //思路2:index如果是零 用头插
        if (index == 0){
            addFirst(data);
            return true;
        }
        //思路3:index如果等于size 用尾插
         if (index == size()){
            addLast(data);
            return true;
        }

       /*  if (this.head == null){
            this.head = node;
            return true;
        }*/

       //思路4:定义一个cur是 插入的 前一个节点  node.next = cur.next;cur.next = node;
        Node cur = this.head;
        int count = 0;
        while (cur != null ){
            if (count == index-1){
                node.next = cur.next;
                cur.next = node;
            }
            cur = cur.next;
            count++;

        }
        return true;
    }

    //得到单链表的长度
    public int size(){
        int count = 0;
        Node cur = this.head;
        while (cur != null){
            cur = cur.next;
            count++;
        }
        return count;
    }



    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key){
        Node cur = this.head;
        while (cur != null){
            if (cur.data == key){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    //删除第一次出现关键字为key的节点
    public void remove(int key){
        //思路1:先判断删除的是否是头结点
       if (this.head.data == key){
           this.head = this.head.next;
           return;
       }
        //思路2:判断是否有key这个 data 调用findKey方法
        Node prev = findKey(key);
        if (prev == null){
            System.out.println("没有key");
            return;
        }
        //思路3:定义一个cur 让cur=prev，next  这个cur 是要删除的节点
        Node cur = prev.next;
        prev.next = cur.next;
    }

    private Node findKey(int key) {
        Node prev = this.head;
        while (prev.next != null) {
            if (prev.next.data == key) {
                return prev;
            }
            prev = prev.next;
        }
        return null;
    }

    //删除所有值为key的节点
    public void removeAllKey(int key){
        Node prev = this.head;
        Node cur = prev.next;
        //思路1:判断头节点的data是否为key 如果是直接 this.head = this.head.next;
        if (prev.data == key ){
            this.head = this.head.next;
        }
        while (cur != null){
            if(cur.data == key){
                prev.next = cur.next;
                cur = cur.next;
            }else {
                prev = cur;
                cur = cur.next;
            }
        }
    }



    //找出倒数第K个节点
    public Node FindKthToTail(int k) {
        if(k <= 0 || k > size()) {
            return null;
        }
        Node fast = this.head;
        Node slow = this.head;
        //1、让fast先走k-1步
        for (int i = 0; i < k-1 ; i++) {
            fast = fast.next;
        }
        //2、让两个引用 一起走  直到 fast.next == null
        // slow 所指的位置就是倒数第K个节点
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    //清空单链表
    public void clear(){
    //this.head = null;
        while(this.head.next != null){
            Node del = this.head.next;
            this.head.next = del.next;
        }
        this.head = null;
    }
    //=================================================================================



   // 2. 反转一个单链表
        //cur是需要反转的节点
    public Node exchangList() {
        Node cur = this.head;
        Node newHand = null;
        Node prev = null;
        while (cur != null){
            Node curNext = cur.next;
            if (curNext == null){
                newHand = cur;
            }
            cur.next = prev;
            prev = cur;
            cur = curNext;

        }
        return newHand;
    }


    //打印单链表
    public void display2(Node newHead){
        Node cur = newHead;
        while(cur != null){
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
    }



   // 3. 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点。
    public Node middleNode(){
        Node fast = this.head;
        Node slow = this.head;
        //让fast两步两步走 slow 一步一步走  走到fast 和fast。next=null时 slow对应的就是中间节点 或者中间节点的第二个
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }



    //4. 输入一个链表，输出该链表中倒数第k个结点。
    public Node node(int k){
        Node fast = this.head;
        Node slow = this.head;
        if (k < 0 ) {
            System.out.println("无该节点");
            return null;
        }
        //如果不用调用size 方法
        /*if (k < 0 || k>size()){
            System.out.println("无该节点");
            return null;
        }*/

        //先让fast 走 k-1步
        for (int i = 0; i < k-1 ; i++) {
            if (fast.next != null) {
                fast = fast.next;
            }else {
                System.out.println("没有该节点");
                return null;
            }
        }
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }



  //6. 编写代码，以给定值x为基准将链表分割成两部分，所有小于x的结点排在大于或等于x的结点之前 。
    public Node partition(int x){
        Node bs = null;
        Node be = null;
        Node as = null;
        Node ae = null;
        Node cur = this.head;
        while (cur != null){
            if (cur.data < x){
                if (bs == null){
                    bs = cur;
                    be = bs;
                }else {
                 be.next = cur;
                 be = be.next;
                }
            }
            if (cur.data > x){
                if (as == null){
                    as = cur; 
                    ae = as;
                }else {
                    ae.next = cur;
                    ae = ae.next;
                }
            }
            cur = cur.next;
        }
        if (bs == null){
            return as;
        }
        be.next = as;
        if (as != null){
            ae.next = null;
        }
        return bs;
    }




   // 7. 在一个   排序    的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 [OJ链接]
    public Node removeSame() {
        Node node = new Node(-1);
        Node tmp = node;
        Node cur = this.head;
        while (cur != null) {
            if (cur.next != null && cur.data == cur.next.data) {
                while (cur.next != null && cur.data == cur.next.data) {
                    cur = cur.next;
                }
                cur = cur.next;
            } else {
                tmp.next = cur;
                tmp = tmp.next;
                cur = cur.next;
            }
        }
        //防止尾节点也重复 删掉之后 保留的 最后一个节点的next保存的是 下一个节点的地址所以直接将tmp置为null
        tmp.next = null;
        return node.next;
    }



   // 8. 判断链表的回文结构。
    public boolean palinDrome (){
        //1.先找到中间节点
        Node fast = this.head;
        Node slow = this.head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //2.局部反转 slow后面那部分 反转
        Node cur = slow.next;
        while (cur != null ) {
            Node curNext = cur.next;
            cur.next = slow;
            slow = cur;
            cur = curNext;
        }
        //3.判断
        while (slow != this.head){
            if (slow.data != this.head.data){
                return false;
            }
            //偶数情况下
            if (this.head.next == slow ){
                return  true;
            }
            slow = slow.next;
            this.head = this.head.next;
        }
        return  true;
    }

    //10. 给定一个链表，判断链表中是否有环。
    public boolean hasCycle(){
        Node fast = this.head;
        Node slow = this.head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                break;
            }
        }
        if (fast == null || fast.next == null){
            return false;
        }
        return true;
    }

        //创建一个有环的单链表
    public void creatLoop(){
        Node cur = this.head;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = this.head.next;
    }

    //11. 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
    public Node hasCycle1(){
        Node fast = this.head;
        Node slow = this.head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                break;
            }
        }
        if (fast == null || fast.next == null){
            return null;
        }
        slow = this.head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }



}





  /*  // 1、无头单向非循环链表实现

    //头插法
    public void addFirst(int data){
    }
    //尾插法
    public void addLast(int data){

    }
    //任意位置插入,第一个数据节点为0号下标
    public boolean addIndex(int index,int data){
        return true;
    }
    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key){
        return true;
    }
    //删除第一次出现关键字为key的节点
    public void remove(int key){

    }
    //删除所有值为key的节点
    public void removeAllKey(int key){

    }
    //得到单链表的长度
    public int size(){
        return -1;
    }
    public void display(){

    }
    public void clear(){

    }*/
