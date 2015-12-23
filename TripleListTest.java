import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TripleListTest {

    @org.junit.Test
    public void TestEmptyListCreation() {
        TripleList<Integer> tripleList = new TripleList<Integer>();
        Assert.assertEquals(0, tripleList.Count());
        Assert.assertNull(tripleList.nextElement);
        Assert.assertNull(tripleList.middleElement);
        Assert.assertNull(tripleList.previousElement);
    }
    @org.junit.Test
    public void TestAddingSingleElement()
    {
        TripleList<Integer> tripleList = new TripleList<>();
        Integer value = 4;
        tripleList.add(value);
        Assert.assertEquals(1, tripleList.Count());
        Assert.assertEquals(value, tripleList.Value());
        Assert.assertNull(tripleList.previousElement);
        Assert.assertNull(tripleList.middleElement);
        Assert.assertNull(tripleList.nextElement);
    }
    @org.junit.Test
    public void TestAddingTwoElements()
    {
        TripleList<Integer> tripleList = new TripleList<>();
        Integer value1 = 4;
        Integer value2 = -9;
        tripleList.add(value1);
        tripleList.add(value2);
        Assert.assertEquals(2, tripleList.Count());
        // checking values
        Assert.assertEquals(value1, tripleList.Value());
        Assert.assertEquals(value2, tripleList.middleElement.Value());
        Assert.assertEquals(tripleList.Value(), tripleList.middleElement.middleElement.Value());
        // checking neighbour Nodes of first element
        Assert.assertNull(tripleList.previousElement);
        Assert.assertNotNull(tripleList.middleElement);
        Assert.assertNull(tripleList.nextElement);
        // checking neighbour Nodes of second element
        Assert.assertNull(tripleList.middleElement.previousElement);
        Assert.assertNull(tripleList.middleElement.nextElement);
    }
    
    @org.junit.Test
    public void testAdd() throws Exception {
        TripleList<Integer> tripleList = new TripleList<>();
        Integer []array = new Integer[] {1,2,3};
        for (Integer x : array) {
            tripleList.add(x);
        }
        Assert.assertNotNull(tripleList);
        Assert.assertNotNull(tripleList.middleElement);
        Assert.assertNotNull(tripleList.nextElement);
        Assert.assertNull(tripleList.nextElement.nextElement);

        int i = 0;
        for (Integer x : tripleList) {

            Assert.assertEquals(x, array[i++]);
        }
    }
    @org.junit.Test
    public void TestAddingTreeElements()
    {
        TripleList<Integer> tripleList = new TripleList<>();
        Integer value1 = 4;
        Integer value2 = -9;
        Integer value3 = 47;
        tripleList.add(value1);
        tripleList.add(value2);
        tripleList.add(value3);
        Assert.assertEquals(3, tripleList.Count());
        // checking values
        Assert.assertEquals(value1, tripleList.Value());
        Assert.assertEquals(value2, tripleList.middleElement.Value());
        Assert.assertEquals(value3, tripleList.nextElement.Value());
        // checking neighbour Nodes of first element
        Assert.assertNull(tripleList.previousElement);
        Assert.assertNotNull(tripleList.middleElement);
        Assert.assertNotNull(tripleList.nextElement);
        // checking neighbour Nodes of second element
        Assert.assertNull(tripleList.middleElement.previousElement);
        Assert.assertNotNull(tripleList.middleElement.middleElement);
        Assert.assertNull(tripleList.middleElement.nextElement);
        // checking neighbour Nodes of third/last element
        Assert.assertNotNull(tripleList.nextElement.previousElement);
        Assert.assertNull(tripleList.nextElement.middleElement);
        Assert.assertNull(tripleList.nextElement.nextElement);
        // checking values
        Assert.assertEquals(value1, tripleList.Value());
        Assert.assertEquals(value2, tripleList.middleElement.Value());
        Assert.assertEquals(value3, tripleList.nextElement.Value());
    }
    @Test
    public void TestAddingFiveElements()
    {
        TripleList<Integer> tripleList = new TripleList<>();
        Integer value1 = 1;
        Integer value2 = 2;
        Integer value3 = 3;
        Integer value4 = 4;
        Integer value5 = 5;
        tripleList.add(value1);
        tripleList.add(value2);
        tripleList.add(value3);
        tripleList.add(value4);
        tripleList.add(value5);
        Assert.assertEquals(5, tripleList.Count());
        // checking values
        Assert.assertEquals(value1, tripleList.Value());
        Assert.assertEquals(value2, tripleList.middleElement.Value());
        Assert.assertEquals(value3, tripleList.nextElement.Value());
        Assert.assertEquals(value4, tripleList.nextElement.middleElement.Value());
        Assert.assertEquals(value5, tripleList.nextElement.nextElement.Value());
    }
    @Test
    public void TestListsEnumerator()
    {
        Double[] values = { 1.1, 3.14, 6.13, 9.99999, 99.001 };
        TripleList<Double> tripleList = new TripleList<>();

        for(Double x : values)
            tripleList.add(x);

        int i = 0;
        for (Double d : tripleList) {
            Assert.assertEquals(values[i++], d);
        }
    }
    @Test
    public void TestListsEnumerator2()
    {
        Double[] values = { 1.1, 3.14, 6.13, 9.99999, 99.001 };
        TripleList<Double> tripleList = new TripleList<>();
        for(Double x : values)
            tripleList.add(x);

        int i = 0;
        Iterator<Double> it = tripleList.iterator();

        while (it.hasNext()) {
            Assert.assertEquals(values[i++], it.next());
        }
    }
    @Test
    public void TestIfNoCycle()
    {
        /** Initialization of the TripleList **/
        final int NUMBER_OF_ELEMENTS = 100;
        TripleList<Integer> tripleList = new TripleList<>();
        for (int i = 0; i < NUMBER_OF_ELEMENTS; ++i) {
            tripleList.add(i);
        }
        /** Created 2 TripleLists, first jumps every single element,
         another every two elements, in out case every two elements means every NextElement**/
        TripleList<Integer> tripleListEverySingleNode = tripleList;
        TripleList<Integer> tripleListEveryTwoNodes = tripleList.nextElement;
        for (int i = 0; i < NUMBER_OF_ELEMENTS * NUMBER_OF_ELEMENTS; ++i)
        {

            Assert.assertNotSame(tripleListEverySingleNode, tripleListEveryTwoNodes);
            JumpToNextElement(this, tripleListEverySingleNode);
            if (null == tripleListEveryTwoNodes.nextElement)
            {
                // if list has end means there are no cycles
                break;
            }
            else
            {
                tripleListEveryTwoNodes = tripleListEveryTwoNodes.nextElement;
            }
        }
    }

    private boolean IsNotLastElement(TripleList<Integer> element)
    {
        return null != element.middleElement;
    }

    private boolean IsMiddleElement(TripleList<Integer> element)
    {
        return element.middleElement != null;
    }
    private static void JumpToNextElement(TripleListTest tripleListTest, TripleList<Integer> element)
    {
        if (tripleListTest.IsNotLastElement(element))
        {
            if (tripleListTest.IsMiddleElement(element))
            {
                if (null != element.middleElement.nextElement)
                {
                    element = element.middleElement.nextElement;
                }
            }
            else
            {
                if (null != element.nextElement)
                {
                    element = element.nextElement;
                }
            }
        }
    }
    @Test
    public void ArrayInitializers()
    {
        TripleList<Integer> tl1 = new TripleList<>(new Integer[]{ 5, 10, 15 }) ;
        Assert.assertEquals(3, tl1.Count());
    }


}
