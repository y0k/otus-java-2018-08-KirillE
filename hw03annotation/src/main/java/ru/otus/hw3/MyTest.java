package ru.otus.hw3;

public class MyTest {

    public MyTest() {
        System.out.println("MyTest");
    }

    @Before
    public void setUp1() {
        System.out.println("Before 1");
    }

    @Before
    public void setUp2() {
        System.out.println("Before 2");
    }

    @Test
    public void test1() {
        System.out.println("test 1");
    }

    @Test
    public void test2() {
        System.out.println("test 2");
    }

    @Test
    public void test3() {
        System.out.println("test 3"); throw new RuntimeException();
    }

    @Test
    private void test4() {
        System.out.println("test 4");
    }

    @After
    public void setDown1() {
        System.out.println("After 1");
    }

    @After
    public void setDown2() {
        System.out.println("After 2");
    }
}
