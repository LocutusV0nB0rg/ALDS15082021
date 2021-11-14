package list;

import java.util.Optional;

public class LinkedListUtils {
    public static ListNodeSL getMinIter(LinkedList linkedList) throws ListEmptyException {
        if (linkedList.isEmpty()) throw new ListEmptyException("List is empty, no min element can be determined");

        ListNodeSL lastHandledElement = linkedList.getFirst();
        ListNodeSL smallestElementYet = linkedList.getFirst();

        while (lastHandledElement.getNext() != null) {
            if (smallestElementYet.getKey() > lastHandledElement.getKey()) {
                smallestElementYet = lastHandledElement;
            }
            lastHandledElement = lastHandledElement.getNext();
        }

        return smallestElementYet;
    }

    public static int getMinRec(LinkedList linkedList) throws ListEmptyException
    {
        ListNodeSL firstNode = Optional.ofNullable(linkedList.getFirst()).orElseThrow(() -> new ListEmptyException("List anchor is null"));

        return getMinRecHelp(firstNode);					// Aufruf der Hilfsfunktion
    }

    private static int getMinRecHelp(ListNodeSL current)
    {
        int currentMin;

        if(current.getNext() == null) return current.getKey();

        currentMin = getMinRecHelp(current.getNext());

        return Math.min(currentMin, current.getKey());
    }
}
