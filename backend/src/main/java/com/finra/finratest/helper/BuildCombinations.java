package com.finra.finratest.helper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class BuildCombinations {

    public static void buildCombinations(String inputString, List<String> result, List<List<Character>> keypadList,
                                        int[] numberArray, String resultString, int index) {
        if (index == -1) {
            result.add(resultString);
            return;
        }
        int digit = numberArray[index];
        int length = keypadList.get(digit).size();
        String part = inputString;
        String x = part.replace((part.substring(part.length() - resultString.length())), resultString);
        result.add(x);
        for (int i = 0; i < length; i++) {
            buildCombinations(inputString, result, keypadList, numberArray, keypadList.get(digit).get(i) + resultString,
                    index - 1);
        }
    }

    public ResponseEntity<Object> possibleCombination(String inputString) throws JSONException {
        List<String> result = new ArrayList<String>();
        List<List<Character>> keypadList = Arrays.asList(
                Arrays.asList('0'),
                Arrays.asList('1'),
                Arrays.asList('A', 'B', 'C'),
                Arrays.asList('D', 'E', 'F'),
                Arrays.asList('G', 'H', 'I'),
                Arrays.asList('J', 'K', 'L'),
                Arrays.asList('M', 'N', 'O'),
                Arrays.asList('P', 'Q', 'R', 'S'),
                Arrays.asList('T', 'U', 'V'),
                Arrays.asList('W', 'X', 'Y', 'Z')
        );

        String[] numbers = inputString.split("");
        int[] numbersArr = new int[numbers.length];
        for (int i = 0; i < numbersArr.length; i++) {
            numbersArr[i] = Integer.parseInt(numbers[i]);
        }
        buildCombinations(inputString, result, keypadList, numbersArr, "", numbersArr.length - 1);
        LinkedHashSet<String> finalResult = new LinkedHashSet<String>(result);
        List list=new ArrayList();
        for (String x : finalResult)
            list.add(x);
        JSONObject jsonObj=new JSONObject();
        JSONArray jsonArray = new JSONArray(list);
        jsonObj.put("count",list.size());
        jsonObj.put("data",jsonArray);
        System.out.println(jsonObj);
        return ResponseEntity.ok(jsonObj.toString());
    }
    private static String buildResponse(int count, LinkedHashSet<String> data) {
        return "{\n" +
                "\tcount : " + count + "\n\tdata : " + data + "}";
    }

}
