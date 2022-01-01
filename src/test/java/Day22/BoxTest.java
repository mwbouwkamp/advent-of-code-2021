package Day22;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void intersect() {
        Box box1 = new Box(0, 2, 0, 2, 0, 2);
        Box box2 = new Box(1, 1, 1, 1, 1, 1);
        List<Box>[] intersect12 = box1.intersect(box2);
        List<Box>[] intersect21 = box2.intersect(box1);

        assertEquals(1, intersect12[0].size());
        assertEquals("[1,1,1]-> [1,1,1]", intersect12[0].get(0).toString());
        assertEquals(6, intersect12[1].size());
        assertEquals("[0,0,0]-> [0,2,2]", intersect12[1].get(0).toString());
        assertEquals("[2,0,0]-> [2,2,2]", intersect12[1].get(1).toString());
        assertEquals("[1,0,0]-> [1,0,2]", intersect12[1].get(2).toString());
        assertEquals("[1,2,0]-> [1,2,2]", intersect12[1].get(3).toString());
        assertEquals("[1,1,0]-> [1,1,0]", intersect12[1].get(4).toString());
        assertEquals("[1,1,2]-> [1,1,2]", intersect12[1].get(5).toString());

        assertEquals(1, intersect21[0].size());
        assertEquals("[1,1,1]-> [1,1,1]", intersect21[0].get(0).toString());
        assertEquals(0, intersect21[1].size());
    }

    @Test
    void getVolume() {
        Box box = new Box(-6, 5, 2, 4, -15, -12);
        assertEquals(144, box.getVolume());
    }

    @Test
    void intersectX() {
        Box box1 = new Box(0, 3, 0, 3, 0, 3);
        Box box2 = new Box(-1, 4, 0, 3, 0, 3);
        Box box3 = new Box(1, 4, 0, 3, 0, 3);
        Box box4 = new Box(-1, 2, 0, 3, 0, 3);
        Box box5 = new Box(1, 2, 0, 3, 0, 3);


        List<Box>[] intersect12 = box1.intersectX(box2);
        List<Box>[] intersect13 = box1.intersectX(box3);
        List<Box>[] intersect14 = box1.intersectX(box4);
        List<Box>[] intersect15 = box1.intersectX(box5);

        assertEquals(1, intersect12[0].size());
        assertEquals("[0,0,0]-> [3,3,3]", intersect12[0].get(0).toString());
        assertEquals(0, intersect12[1].size());

        assertEquals(1, intersect13[0].size());
        assertEquals("[1,0,0]-> [3,3,3]", intersect13[0].get(0).toString());
        assertEquals(1, intersect13[1].size());
        assertEquals("[0,0,0]-> [0,3,3]", intersect13[1].get(0).toString());

        assertEquals(1, intersect14[0].size());
        assertEquals("[0,0,0]-> [2,3,3]", intersect14[0].get(0).toString());
        assertEquals(1, intersect14[1].size());
        assertEquals("[3,0,0]-> [3,3,3]", intersect14[1].get(0).toString());

        assertEquals(1, intersect15[0].size());
        assertEquals("[1,0,0]-> [2,3,3]", intersect15[0].get(0).toString());
        assertEquals(2, intersect15[1].size());
        assertEquals("[0,0,0]-> [0,3,3]", intersect15[1].get(0).toString());
        assertEquals("[3,0,0]-> [3,3,3]", intersect15[1].get(1).toString());
    }

    @Test
    void intersectY() {
        Box box1 = new Box(0, 3, 0, 3, 0, 3);
        Box box2 = new Box(0, 3, -1, 4, 0, 3);
        Box box3 = new Box(0, 3, 1, 4, 0, 3);
        Box box4 = new Box(0, 3, -1, 2, 0, 3);
        Box box5 = new Box(0, 3, 1, 2, 0, 3);


        List<Box>[] intersect12 = box1.intersectY(box2);
        List<Box>[] intersect13 = box1.intersectY(box3);
        List<Box>[] intersect14 = box1.intersectY(box4);
        List<Box>[] intersect15 = box1.intersectY(box5);

        assertEquals(1, intersect12[0].size());
        assertEquals("[0,0,0]-> [3,3,3]", intersect12[0].get(0).toString());
        assertEquals(0, intersect12[1].size());

        assertEquals(1, intersect13[0].size());
        assertEquals("[0,1,0]-> [3,3,3]", intersect13[0].get(0).toString());  //
        assertEquals(1, intersect13[1].size());
        assertEquals("[0,0,0]-> [3,0,3]", intersect13[1].get(0).toString());

        assertEquals(1, intersect14[0].size());
        assertEquals("[0,0,0]-> [3,2,3]", intersect14[0].get(0).toString());
        assertEquals(1, intersect14[1].size());
        assertEquals("[0,3,0]-> [3,3,3]", intersect14[1].get(0).toString());

        assertEquals(1, intersect15[0].size());
        assertEquals("[0,1,0]-> [3,2,3]", intersect15[0].get(0).toString());
        assertEquals(2, intersect15[1].size());
        assertEquals("[0,0,0]-> [3,0,3]", intersect15[1].get(0).toString());
        assertEquals("[0,3,0]-> [3,3,3]", intersect15[1].get(1).toString());
    }

    @Test
    void intersectZ() {
        Box box1 = new Box(0, 3, 0, 3, 0, 3);
        Box box2 = new Box(0, 3, 0, 3, -1, 4);
        Box box3 = new Box(0, 3, 0, 3, 1, 4);
        Box box4 = new Box(0, 3, 0, 3, -1, 2);
        Box box5 = new Box(0, 3, 0, 3, 1, 2);


        List<Box>[] intersect12 = box1.intersectZ(box2);
        List<Box>[] intersect13 = box1.intersectZ(box3);
        List<Box>[] intersect14 = box1.intersectZ(box4);
        List<Box>[] intersect15 = box1.intersectZ(box5);

        assertEquals(1, intersect12[0].size());
        assertEquals("[0,0,0]-> [3,3,3]", intersect12[0].get(0).toString());
        assertEquals(0, intersect12[1].size());

        assertEquals(1, intersect13[0].size());
        assertEquals("[0,0,1]-> [3,3,3]", intersect13[0].get(0).toString());
        assertEquals(1, intersect13[1].size());
        assertEquals("[0,0,0]-> [3,3,0]", intersect13[1].get(0).toString());


//        Box box1 = new Box(0, 3, 0, 3, 0, 3);
//        Box box4 = new Box(0, 3, 0, 3, -1, 2);

        assertEquals(1, intersect14[0].size()); ///
        assertEquals("[0,0,0]-> [3,3,2]", intersect14[0].get(0).toString());
        assertEquals(1, intersect14[1].size());
        assertEquals("[0,0,3]-> [3,3,3]", intersect14[1].get(0).toString());

        assertEquals(1, intersect15[0].size());
        assertEquals("[0,0,1]-> [3,3,2]", intersect15[0].get(0).toString());
        assertEquals(2, intersect15[1].size());
        assertEquals("[0,0,0]-> [3,3,0]", intersect15[1].get(0).toString());
        assertEquals("[0,0,3]-> [3,3,3]", intersect15[1].get(1).toString());
    }
}