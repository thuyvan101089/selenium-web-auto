package test_data;

import com.google.gson.Gson;

public class testGson {
    public static void main(String[] args) {
        //   testGsonSample();
        //   testBuilderMethod();
        testDataArray();
    }


    public static void testDataArray() {
        String relativeFileLocation = "/src/main/java/test_data/CheapComputerDataList.json";
        ComputerData[] computerData = DataObjectBuilder.buildDataObjectFrom(relativeFileLocation, ComputerData[].class);
        for (ComputerData computerData1 : computerData) {
            System.out.println(computerData1.getProcessor());
            System.out.println(computerData1.getRam());
            System.out.println(computerData1.getHdd());
            System.out.println(computerData1.getSoftware());

        }


    }

    public static void testBuilderMethod() {
        String relativeFileLocation = "/src/main/java/test_data/CheapComputerDataList.json";
        ComputerData computerData = DataObjectBuilder.buildDataObjectFrom(relativeFileLocation, ComputerData.class);
        System.out.println(computerData);
        System.out.println(computerData.getProcessor());
        System.out.println(computerData.getRam());
        System.out.println(computerData.getHdd());
        System.out.println(computerData.getSoftware());

    }

    public static void testGsonSample() {
        String JsonString = "{\n" +
                "  \"processor\": \"2.5 GHz\",\n" +
                "  \"ram\": \"2GB\",\n" +
                "  \"os\": \"Ubuntu\",\n" +
                "  \"hdd\": \"22GB\",\n" +
                "  \"software\": \"Viewer\"\n" +
                "  \n" +
                "}";
        Gson gson = new Gson();
        //From Json to object
        ComputerData computerData = gson.fromJson(JsonString, ComputerData.class);
        System.out.println(computerData);
        // From object to Json
        System.out.println(gson.toJson(computerData));
    }
}
