package com.rfb.demo.rxjavatest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.rfb.demo.rxjavatest.annotation.TestAnnotationUtil;
import com.rfb.demo.rxjavatest.bean.Outer;
import com.rfb.demo.rxjavatest.codebyte.FirstReverse;
import com.rfb.demo.rxjavatest.codebyte.LongestWord;
import com.rfb.demo.rxjavatest.collection.Maptest;
import com.rfb.demo.rxjavatest.funny.KMP;
import com.rfb.demo.rxjavatest.funny.MinMax;
import com.rfb.demo.rxjavatest.funny.PerfectSqrt;
import com.rfb.demo.rxjavatest.funny.RPN;
import com.rfb.demo.rxjavatest.jaskson.JacksonTest;
import com.rfb.demo.rxjavatest.matcher.TestMatcher;
import com.rfb.demo.rxjavatest.poet.HowToJavaPoetDemo;
import com.rfb.demo.rxjavatest.poet.PoetTest;
import com.rfb.demo.rxjavatest.toolkit.StringXmlUtils;
import com.rfb.demo.rxjavatest.utils.*;
import com.rfb.demo.rxjavatest.utils.strategy.BaseRemindStrategy;
import com.rfb.demo.rxjavatest.utils.strategy.MockStrategy;
import com.rfb.demo.rxjavatest.utils.strategy.RemindStrategyTime;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;


/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class Main {

    public static void checkString(){

        String path1 = "G:\\temp\\1\\strings-develop.xml";
        String path2 = "G:\\temp\\1\\strings-memberRole.xml";

        StringXmlUtils.divideUntranslateString(path1, path2);
    }

    private static String dump(Object o) {
        StringBuffer buffer = new StringBuffer();
        Class<?> oClass = o.getClass();
        if (oClass.isArray()) {
            buffer.append("[");
            for (int i = 0; i < Array.getLength(o); i++) {
                if (i > 0)
                    buffer.append(",");
                Object value = Array.get(o, i);
                buffer.append(value.getClass().isArray() ? dump(value) : value);
            }
            buffer.append("]");
        } else {
            buffer.append("{");
            while (oClass != null) {
                Field[] fields = oClass.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    if (buffer.length() > 1)
                        buffer.append(",");
                    fields[i].setAccessible(true);
                    buffer.append(fields[i].getName());
                    buffer.append("=");
                    try {
                        Object value = fields[i].get(o);
                        if (value != null) {
                            buffer.append(value.getClass().isArray() ? dump(value)
                                    : value);
                        }
                    } catch (IllegalAccessException e) {
                    }
                }
                oClass = oClass.getSuperclass();
            }
            buffer.append("}");
        }
        return buffer.toString();
    }



    public static void testxxx(){


        RemindStrategyTime time = new RemindStrategyTime(" 0 0 0", 1, System.currentTimeMillis(),
                System.currentTimeMillis());
        List<BaseRemindStrategy> list = new ArrayList<BaseRemindStrategy>();
        list.add(time);
        MockStrategy mockStrategy = new MockStrategy();
        mockStrategy.setStrategies(list);

        ObjectMapper mMapper = new ObjectMapper();

        mMapper.addHandler(new DeserializationProblemHandler() {
            @Override
            public Object handleWeirdStringValue(DeserializationContext ctxt, Class<?> targetType, String valueToConvert, String failureMsg) throws IOException {
                long l = TimeUtils.getTimeByString(valueToConvert);
                return l;
            }
        });

        try {
            String str = mMapper.writeValueAsString(mockStrategy);

            MockStrategy newData = mMapper.readValue(str, MockStrategy.class);

            if(newData == null){
                System.out.println("fuck");
            }
        } catch (Exception pE) {
            pE.printStackTrace();
        }

    }

    public static void test111(){

        try{
            //初始化各内外实例
            Outer test = new Outer();
            Outer.FirstInner first = test.new FirstInner();
            Outer.FirstInner.SecondInner second = first.new SecondInner();
            Outer.FirstInner.SecondInner.ThirdInner third=second.new ThirdInner();
            //Outer,this$0
            Field outerfield = first.getClass().getDeclaredField("this$0");
            outerfield.setAccessible(true);
            Object object = outerfield.get(first);
            System.out.println(object instanceof Outer);
            //FirstInner,this$1
            Field firstInnerfied = second.getClass().getDeclaredField("this$1");
            firstInnerfied.setAccessible(true);
            object = firstInnerfied.get(second);
            System.out.println(object instanceof Outer.FirstInner);
            //SecondInner,this$2
            Field secondInnerfield = third.getClass().getDeclaredField("this$2");
            secondInnerfield.setAccessible(true);
            object = secondInnerfield.get(third);
            System.out.println(object instanceof Outer.FirstInner.SecondInner);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static class TestBean{

        public int i;

        public String string;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestBean testBean = (TestBean) o;

            if (i != testBean.i) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return i;
        }
    }

    public static void testHashSet(){

        Set<TestBean> sets = new HashSet<TestBean>();

        TestBean testBean = new TestBean();
        testBean.i = 1;
        testBean.string = "1";
        sets.add(testBean);


        testBean = new TestBean();
        testBean.i = 1;
        testBean.string = "2";
        sets.remove(testBean);
        sets.add(testBean);

        for(TestBean testBean1:sets){
            System.out.println(testBean1.i+" "+testBean1.string);
        }

    }

    public static void main(String[] args) {

        TestAnnotationUtil.test();

//        HowToJavaPoetDemo.test();

//        PoetTest poetTest = new PoetTest();
//        poetTest.helloTest();

//        testHashSet();

//        Maptest.test();
//        MinMax minMax = new MinMax();
//        minMax.test();
//        test111();
//        RPN rpn = new RPN();
//        rpn.test("3 - 4 + 5");
//        rpn.test("3 - 4 * 5");
//        rpn.test("3 * 4 + 5");
//        rpn.test("3 / 4 * 5");
//        rpn.test("3 * 4 / 5");
//        rpn.test("3 - ( 4 + 5 )");
//        rpn.test("1 * ( 2 * 3 + ( 4 * 5 ) * ( 6 - 7 ) )");
//        rpn.test("1 * ( ( ( 2 + 3 ) * 4 + 5 ) * 6 )");


        //        System.out.println(LongestWord.LongestWord("123456789 98765432"));

//        System.out.println(FirstReverse.FirstReverse("123456789"));
//        PerfectSqrt.test();
//        testxxx();
//        JacksonTest.test();

//        KMP.test();

//        TestMatcher matcher = new TestMatcher();
//        matcher.testGroup();

//        Solar solar = new Solar();
//        solar.solarYear = 1990;
//        solar.solarMonth = 6;
//        solar.solarDay = 30;
//
////        Lunar l= new Lunar();
////        l.isleap=false;
////        l.lunarDay=12;
////        l.lunarMonth=7;
////        l.lunarYear=2100;
//        Lunar lunar = LunarSolarConverter.SolarToLunar(solar);
//        System.out.println(dump(lunar));
////        solar = LunarSolarConverter.LunarToSolar(l);
//        System.out.println(dump(solar));
//        System.out.println(LunarSolarConverter.lunarYearToGanZhi(2100));

//        try{
////            System.out.println(CalendarUtil.lunarToSolar("19901204", false));
////            System.out.println(CalendarUtil.lunarToSolar("19841021", true));
////            System.out.println("************");
////            System.out.println(CalendarUtil.solarToLunar("19000923"));
////            System.out.println(CalendarUtil.solarToLunar("19000924"));
////            System.out.println(CalendarUtil.solarToLunar("19001022"));
////            System.out.println(CalendarUtil.solarToLunar("19001023"));
////
////            System.out.println(CalendarUtil.solarToLunar("19900630"));
////            System.out.println(CalendarUtil.solarToLunar("19841213"));
//            System.out.println(CalendarUtil.solarToLunar("19900630"));
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }


//        System.out.println(String.valueOf("asdfadf"));
//        System.out.println(String.valueOf(11111));
//
//        List<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//
//        Collections.sort(list, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return (o1-o2);
//            }
//        });
//
//        for(Integer integer:list){
//            System.out.println(integer);
//        }

//        Random random = new Random();
//        random.nextLong();

//        Map<NodeKey, String> map = new HashMap<NodeKey, String>();
//        map.put(NodeKey.create(0L, NodeType.node), "node0");
//        map.put(NodeKey.create(1L, NodeType.node), "node1");
//        map.put(NodeKey.create(2L, NodeType.node), "node2");
//        map.put(NodeKey.create(0L, NodeType.user), "user0");
//        map.put(NodeKey.create(1L, NodeType.user), "user1");
//
//        System.out.println(map.get(NodeKey.create(0L, NodeType.node)));
//        System.out.println(map.get(NodeKey.create(2L, NodeType.node)));
//        System.out.println(map.get(NodeKey.create(0L, NodeType.user)));

//        RxjavaTest rxjavaTest = new RxjavaTest();
//        rxjavaTest.testGetValue();
////
//        try{
//
//            Thread.sleep(20000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("end");

//        List<String> list = new ArrayList<String>();
//        list.add("0");
//        list.add("1");
//        list.add("2");
//        list.add("3");
//
////        List<String> list2 = new ArrayList<String>();
////        list.add("10");
////        list.add("11");
////        list.add("12");
////        list.add("13");
//
//        list.add(0, "insert");
//
//        for(String string:list){
//            System.out.println(string);
//        }

//        Set<String> strings = new HashSet<String>();
//
//        for(String str:strings){
//
//        }
//
//        byte b = -0x80;
//
//        System.out.println((b & 0xFF) + "");
//        if((b & 0xFF) == 0xFF){
//            System.out.println("true");
//        }else{
//            System.out.println("false");
//        }




//        test2();
//        List<String> list = new ArrayList<String>();
//        list.add("0");
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
//
//        list.remove(2);
//        list.add(2, "20");
//
//        for(String item:list){
//            System.out.println(item);
//        }

//        test1();

//        test0();
//        Observable
//                .just("1")
//                .map(new Func1<String, Object>() {
//                    @Override
//                    public Object call(String s) {
//
//                        return null;
//                    }
//                });
//
//        Game1 game1 = new Game1();
//        game1.test();

//        List<String> list = new ArrayList<String>();
//        list.remove(null);

//        RecoverPublishSubjectV1Demo demo = new RecoverPublishSubjectV1Demo();
//        demo.test();

//        RecoverPublishSubjectV2Demo demo = new RecoverPublishSubjectV2Demo();
//        demo.test();
//
//        RecoverPublishSubjectV3Demo demo = new RecoverPublishSubjectV3Demo();
//        demo.test();
//
//        RecoverPublishSubjectV4Demo demo = new RecoverPublishSubjectV4Demo();
//        demo.test();

//          RecoverPublishSubjectV5Demo demo = new RecoverPublishSubjectV5Demo();
//          demo.test();

//        Dtgh1 dtgh1 = new Dtgh1();
//        dtgh1.test();

//        HowManyZero howManyZero = new HowManyZero();
//        howManyZero.test(10);

//        MaxRect maxRect = new MaxRect();
//        maxRect.testXX();

//        RxjavaTest test = new RxjavaTest();
//        test.testPublishXXX();


//
//        try{
//            Thread.sleep(3000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        GrayCode greyCode = new GrayCode(5);
//        greyCode.start();

//        String s = "xxxx";
//        System.out.println(s.indexOf("?"));

//        String s = "    asdfa a\r\n \r\n   ";
//        s = s.trim();
//        System.out.println(s);

//        String local = ".xxx/asdaasdf";
//
//        int lastIndex = local.lastIndexOf(".");
//        if(lastIndex >= 0){
//            System.out.println(local.substring(lastIndex+1, local.length()));
//        }

        
//        RxjavaTest rxjavaTest = new RxjavaTest();
//        rxjavaTest.testUnsubscribeDoOnNext();
//
//        try{
//
//            Thread.sleep(15000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        String path1 = "G:\\temp\\1\\strings-develop.xml";
//        File file = new File(path1);
//        System.out.println(file.getPath());
//        System.out.println(file);

//        checkString();
//        String s = null;
//        String ss = "xxx";
//        if(ss.equals(s)){
//            System.out.println("xxx");
//        }else{
//            System.out.println("eee");
//        }

//        List<Integer> list = new ArrayList<Integer>();
//        for(int i = 0; i < 10; i++){
//            list.add(i);
//        }
//
//        list = list.subList(2,4);
//        for(int i = 0; i < list.size(); i++){
//            System.out.println(list.get(i));
//        }

//        RxjavaTest test = new RxjavaTest();
//        test.testBehavior();
//
//        try{
//
//            Thread.sleep(10000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        List<Integer> test = new ArrayList<Integer>();
//        test.add(0);
//        test.add(2);
//
//        test = test.subList(0, 3);
//        System.out.println(test.get(2));

//        List<A> list = new ArrayList<A>();
//
//        list.add(new A(0));
//        list.add(new A(1));
//        list.add(new A(2));
//        list.add(new A(3));
//        list.add(new A(4));
//        list.add(new A(5));
//        list.add(new A(6));
//        list.add(new A(7));
//
//        Collections.sort(list);
//
//        for(A a:list){
//            System.out.println(a.a);
//        }

//        String temp = "key_image_url=aHR0cDovL2JldGFjcy4xMDEuY29tL3YwLjEvcmVhbFVybD9kZW50cnlJZD0zMzc2OWVmMi0xNWI1LTRjYWItOTEyZC1mMzI0MmIwYmM5NDAmc2l6ZT0xMjA=";
//
//        int index = temp.indexOf("=");
//        System.out.println(temp.substring(0, index)+" "+temp.substring(index+1, temp.length()));
//
//        String s = "0123456";
//        System.out.println("index " + s.indexOf("a"));

//        String s = String.format("xxx%dxxx", 10);
//        System.out.println(s);


//        Subscription sub = Observable.interval(1, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        System.out.println("action1 "+aLong);
//                    }
//                });
//
//        try{
//
//            Thread.sleep(10000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        sub.unsubscribe();
//
//        try{
//
//            Thread.sleep(3000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("end");

//        RxjavaTest rxjavaTest = new RxjavaTest();
//        rxjavaTest.testMergeComplete();
//
//        rxjavaTest.getNotify().subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                PrintUtil.print("xxoo1 "+s);
//            }
//        });
//
//        RxjavaTest rxjavaTest = new RxjavaTest();
//        rxjavaTest.onError();
//
//        try{
//
//            Thread.sleep(10000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("end");

//        RxjavaTest rxjavaTest = new RxjavaTest();
//
//        rxjavaTest.getNotify2().subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                PrintUtil.print("xxoo1 "+s);
//            }
//        });
//
//        RxjavaTest rxjavaTest = new RxjavaTest();
//        rxjavaTest.testError();
//
//        try{
//
//            Thread.sleep(5000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("end");

//        B b = new B();
//        System.out.println("Xxxxx "+b.getString());
//        RxjavaTest rxjavaTest = new RxjavaTest();
//        rxjavaTest.testFinalSub();
//
//        try{
//
//            Thread.sleep(3000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("end");

//        Map<String, Integer> map = new HashMap<String, Integer>();
//        map.put("1", 1);
//
//        int i = map.get("2");
//        System.out.println(i);


//        RxjavaTest rxjavaTest = new RxjavaTest();
//        rxjavaTest.testFilter();
//
//
//        try{
//
//            Thread.sleep(3000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("end");

//        MainFrame mainFrame = new MainFrame();
//        mainFrame.show();

//        RxjavaTest rxjavaTest = new RxjavaTest();
//        rxjavaTest.testBehaviorPublish();
//
//        try{
//
//            Thread.sleep(5000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("end");


//        if( null instanceof Object){
//            System.out.println("xxxooo");
//        }else{
//            System.out.println("aaaaaaa");
//        }

//        IParam param1 = new Param();
//        IParam param2 = param1;
//
//        System.out.println(param2.equals(param1));

//        Son son = new Son();
//        son.print();

//        Tree tree0 = new Tree();
//        tree0.setExpand(true);
//        tree0.setDataProvider(new Tree.IDataProvider() {
//            @Override
//            public Object getData() {
//                return "tree0";
//            }
//        });
//
//        Tree tree00 = new Tree(tree0);
//        tree00.setExpand(true);
//        tree00.setDataProvider(new Tree.IDataProvider() {
//            @Override
//            public Object getData() {
//                return "tree00";
//            }
//        });
//
//        Tree tree01 = new Tree(tree0);
//        tree01.setExpand(true);
//        tree01.setDataProvider(new Tree.IDataProvider() {
//            @Override
//            public Object getData() {
//                return "tree01";
//            }
//        });
//
//        Tree tree000 = new Tree(tree00);
//        tree000.setExpand(true);
//        tree000.setDataProvider(new Tree.IDataProvider() {
//            @Override
//            public Object getData() {
//                return "tree000";
//            }
//        });
//
//        Tree tree001 = new Tree(tree00);
//        tree001.setExpand(true);
//        tree001.setDataProvider(new Tree.IDataProvider<String>() {
//            @Override
//            public String getData() {
//                return "tree001";
//            }
//        });
//
//        Tree tree002 = new Tree(tree00);
//        tree002.setExpand(true);
//        tree002.setDataProvider(new Tree.IDataProvider() {
//            @Override
//            public Object getData() {
//                return "tree002";
//            }
//        });
//
//        Tree tree003 = new Tree(tree00);
//        tree003.setExpand(true);
//        tree003.setDataProvider(new Tree.IDataProvider() {
//            @Override
//            public Object getData() {
//                return "tree003";
//            }
//        });
//
//        Tree tree010 = new Tree(tree01);
//        tree010.setExpand(true);
//        tree010.setDataProvider(new Tree.IDataProvider() {
//            @Override
//            public Object getData() {
//                return "tree010";
//            }
//        });
//
//        Tree tree011 = new Tree(tree01);
//        tree011.setExpand(true);
//        tree011.setDataProvider(new Tree.IDataProvider() {
//            @Override
//            public Object getData() {
//                return "tree011";
//            }
//        });
//
//        List<Tree> treeList = new ArrayList<Tree>();
//        TreeUtil.throughTreeBefore(tree0, treeList);
//        for(Tree tree:treeList){
//
//            for(int i = 0; i < tree.getLevel(); i++){
//                System.out.print(" ");
//            }
//            System.out.println(tree.getDataProvider().getData().toString());
//        }

//        test();
    }

    public static void test1(){


        List<UserInfoItem> userInfos = new ArrayList<UserInfoItem>();

        UserInfoItem infoItem = new UserInfoItem();
        infoItem.setType("NAME");
        userInfos.add(infoItem);

        infoItem = new UserInfoItem();
        infoItem.setType("NAME");
        infoItem.setText("NAME1");
        userInfos.add(infoItem);

        infoItem = new UserInfoItem();
        infoItem.setType("NAME");
        infoItem.setText("NAME1");
        userInfos.add(infoItem);

        infoItem = new UserInfoItem();
        infoItem.setType("NAME");
        userInfos.add(infoItem);

        infoItem = new UserInfoItem();
        infoItem.setType("NAME");
        infoItem.setText("NAME2");
        userInfos.add(infoItem);

        infoItem = new UserInfoItem();
        infoItem.setType("NAME");
        userInfos.add(infoItem);

        infoItem = new UserInfoItem();
        infoItem.setType("NAME");
        userInfos.add(infoItem);

        infoItem = new UserInfoItem();
        infoItem.setType("NAME1");
        userInfos.add(infoItem);

        infoItem = new UserInfoItem();
        infoItem.setType("NAME");
        userInfos.add(infoItem);

        infoItem = new UserInfoItem();
        infoItem.setType("NAME1");
        userInfos.add(infoItem);


        if(userInfos != null && !userInfos.isEmpty()) {

            int i = 0;
            while (i < userInfos.size()-1) {

                UserInfoItem item = userInfos.get(i);
                UserInfoItem nextItem = userInfos.get(i + 1);

                if("NAME".equals(item.getType()) && "NAME".equals(nextItem.getType())){

                    if(TextUtils.isEmpty(nextItem.getText())){
                        //delete next item when text is null
                        userInfos.remove(i+1);
                    }else if(TextUtils.isEmpty(item.getText())){
                        //delete item when text is null
                        userInfos.remove(i);
                    }else if(nextItem.getText().equals(item.getText())){
                        //has same value delete nextitem
                        userInfos.remove(i+1);
                    }else{
                        i++;
                    }
                    continue;
                }
                i++;
            }
        }

        for(UserInfoItem infoItem1:userInfos){
            System.out.println(infoItem1.getType()+" "+infoItem1.getText());
        }

    }

    public static class TextUtils{

        public static boolean isEmpty(String value){
            if(value == null || "".equals(value)){
                return true;
            }
            return false;
        }

    }

    public static class UserInfoItem{

        private String mText;

        private String mType;

        public void setText(String text) {
            mText = text;
        }

        public void setType(String type) {
            mType = type;
        }

        public String getText() {
            return mText;
        }

        public String getType() {
            return mType;
        }
    }

    public static void test0(){

        List<Integer> list = new ArrayList<Integer>();

        list.add(0);
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(5);


        int i = 1;
        while (i < list.size()) {

            int item = list.get(i);
            int lastItem = list.get(i - 1);
            if (lastItem == item) {
                list.remove(i);
            } else {
                i++;
            }
        }

        for(i = 0; i < list.size(); i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println();

    }

    public static void test(){

        try{
            throw new Exception();
        }catch (Exception e){
            System.out.println("Exception");
            return;
        }finally {
            System.out.println("finally");
        }

    }

    public static void sort(int[] a){

        int left = 0;
        int right = a.length-1;

        while(left < right){

            if(a[left] % 2 == 1){
                left++;
                continue;
            }

            if(a[right] % 2 == 0){
                right--;
                continue;
            }

            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
        }
    }

    public static void test2(){

        int a[] = new int[20];
        Random rd = new Random(System.currentTimeMillis());
        for(int i = 0; i < a.length; i++){
            a[i] = rd.nextInt(1000);
        }

        PrintUtil.printIntArray(a);
        sort(a);
        PrintUtil.printIntArray(a);
    }

}

