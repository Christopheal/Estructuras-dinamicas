import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList doublyList = new DoublyLinkedList();

        int choice;
        do {
            System.out.println("\nLista Doblemente enlazada");
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al Final");
            System.out.println("3. Recorrer hacia adelante");
            System.out.println("4. Recorrer hacia atrás");
            System.out.println("5. Mostrar tamaño de la lista");
            System.out.println("6. Mostrar si la lista esta vacía");
            System.out.println("7. Buscar elemento por valor");
            System.out.println("8. Buscar elemento por índice");
            System.out.println("9. Borrar un Elemento");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el elemento a insertar al inicio: ");
                    int elementAtStart = scanner.nextInt();
                    doublyList.insertAtHead(elementAtStart);
                    break;
                case 2:
                    System.out.print("Ingrese el elemento a insertar al final: ");
                    int elementAtEnd = scanner.nextInt();
                    doublyList.insertAtTail(elementAtEnd);
                    break;
                case 3:
                    System.out.println("Recorriendo hacia adelante:");
                    doublyList.traverseForward();
                    break;
                case 4:
                    System.out.println("Recorriendo hacia atrás:");
                    doublyList.traverseBackward();
                    break;
                case 5:
                    System.out.println("Tamaño de la lista doblemente enlazada: " + doublyList.size());
                    break;
                case 6:
                    System.out.println("¿La lista doblemente enlazada está vacía? " + doublyList.isEmpty());
                    break;
                case 7:
                    System.out.print("Ingrese el elemento a buscar: ");
                    int searchElement = scanner.nextInt();
                    System.out.println("Elemento encontrado: " + doublyList.searchByValue(searchElement));
                    break;
                case 8:
                    System.out.print("Ingrese el índice a buscar: ");
                    int index = scanner.nextInt();
                    System.out.println("Elemento en el índice " + index + ": " + doublyList.searchByIndex(index));
                    break;
                case 9:
                    System.out.print("Ingrese el elemento a borrar: ");
                    int deleteElement = scanner.nextInt();
                    doublyList.deleteByValue(deleteElement);
                    break;
                case 10:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (choice != 10);

        scanner.close();
    }
}

class Node {
    int data;
    Node prev;
    Node next;

    public Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void insertAtTail(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void traverseForward() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void traverseBackward() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean searchByValue(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int searchByIndex(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node current = head;
        int count = 0;
        while (count < index) {
            current = current.next;
            count++;
        }
        return current.data;
    }

    public void deleteByValue(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } else if (current == head) {
                    head = head.next;
                    head.prev = null;
                } else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
        System.out.println("El elemento no se encontró en la lista.");
    }
}
