import java.util.HashMap;
import java.util.Map;

class Song {
    private String name;

    public Song(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class DoublyLinkedListNode {
    private Song song;
    private DoublyLinkedListNode prev;
    private DoublyLinkedListNode next;

    public DoublyLinkedListNode(Song song) {
        this.song = song;
    }

    public Song getSong() {
        return song;
    }

    public DoublyLinkedListNode getPrev() {
        return prev;
    }

    public void setPrev(DoublyLinkedListNode prev) {
        this.prev = prev;
    }

    public DoublyLinkedListNode getNext() {
        return next;
    }

    public void setNext(DoublyLinkedListNode next) {
        this.next = next;
    }
}

class RecentlyPlayedStore {
    private final int capacity;
    private Map<String, DoublyLinkedListNode> map;
    private DoublyLinkedListNode head;
    private DoublyLinkedListNode tail;

    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = null;
        tail = null;
    }

    public void addSong(String user, Song song) {
        DoublyLinkedListNode node;
        if (map.containsKey(user)) {
            node = map.get(user);
            removeNode(node);
        } else {
            node = new DoublyLinkedListNode(song);
            map.put(user, node);
        }
        addNodeToFront(node);
        if (map.size() > capacity) {
            removeTailNode();
        }
    }

    private void removeNode(DoublyLinkedListNode node) {
        DoublyLinkedListNode prev = node.getPrev();
        DoublyLinkedListNode next = node.getNext();
        if (prev != null) {
            prev.setNext(next);
        } else {
            head = next;
        }
        if (next != null) {
            next.setPrev(prev);
        } else {
            tail = prev;
        }
    }

    private void addNodeToFront(DoublyLinkedListNode node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        }
    }

    private void removeTailNode() {
        if (tail != null) {
            String userToRemove = tail.getSong().getName();
            removeNode(tail);
            map.remove(userToRemove);
        }
    }

    public void printRecentlyPlayedSongs(String user) {
        DoublyLinkedListNode node = map.get(user);
        while (node != null) {
            System.out.println(node.getSong().getName());
            node = node.getNext();
        }
    }
}

public class Song1 {
    public static void main(String[] args) {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

        store.addSong("user1", new Song("song1"));
        store.addSong("user2", new Song("song2"));
        store.addSong("user1", new Song("song3"));
        store.addSong("user3", new Song("song4"));
        store.addSong("user2", new Song("song5"));

        store.printRecentlyPlayedSongs("user1");
        store.printRecentlyPlayedSongs("user2");
        store.printRecentlyPlayedSongs("user3");
    }
}

