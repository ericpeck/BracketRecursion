import java.util.*;

public class BracketRecursion{
  
  public static void main(String args[]){
    
    String str1input = "2abc2[d2[f]g]ab3[c]m"; 
    String str2input = "2abc2[da[f]g]ab3[c]m";
    
    String correct1 = "2abcdffgdffgabcccm";
    String correct2 = "2abcdafgdafgabcccm";
       
    String returnStr1 = convertFunc(str1input);
    String returnStr2 = convertFunc(str2input);
    
    System.out.println("Test Case 1:"+returnStr1.equals(returnStr1));
    System.out.println("Test Case 2:"+returnStr2.equals(returnStr2));
    
  }
  
  public static String convertFunc(String value){
    
    //This part takes the input String value and converts it to an ArrayList of chars.
    ArrayList<Character> charAL = new ArrayList<Character>();
    for(char c : value.toCharArray()){
      charAL.add(c);
    }
    
    //These are varables used to assist in the recursion section to compute the final output
    ArrayList<Character> emptyCharAL = new ArrayList<Character>();
    int indexCounter = 0;
    Stack<Character> myStack = new Stack<Character>();
    
    //We use the magicFunc method to return our input as an ArraList
    ArrayList<Character> finalCharAL = recursionFunc(charAL, myStack, indexCounter);
    //System.out.println(Arrays.toString(finalCharAL.toArray()));      Prints out returned array for checking purposes
    
    //We take our final ArrayList and convert it to a string.
    StringBuilder finalString = new StringBuilder(finalCharAL.size());
    for(Character a : finalCharAL){
        finalString.append(a); 
    }
    
    return finalString.toString();

  }
  
  public static ArrayList<Character> recursionFunc(ArrayList<Character> modelArray, Stack<Character> myStack, int index){
    
    int modelArraySize = modelArray.size();
    
    //Basecase IF statement
    if(index >= modelArraySize){
      
      ArrayList<Character> finalArray = new ArrayList<Character>();      
      while(myStack.empty() != true){
          finalArray.add(myStack.pop());
      }
      Collections.reverse(finalArray);
      
      return finalArray; 
    }
    //Catch the ']' and performs necessery function
    //The heart of the computation is used with a Stack and temporary ArrayList
    else if(modelArray.get(index) == ']'){      
      ArrayList<Character> temp = new ArrayList<Character>();
      while(myStack.peek() != '['){
        temp.add(myStack.pop());
      }
        myStack.pop();
        int x = Character.getNumericValue(myStack.peek());
        if(x >= 2 && x<10){
          myStack.pop();
          
          for(int i = 0; i < x; i++){            
            for(int j = 0; j < temp.size(); j++){
               myStack.push(temp.get(temp.size()-1-j));
            }           
          }
        }else{
          for(int j = 0; j < temp.size(); j++){
               myStack.push(temp.get(temp.size()-1-j));
            }
        }
      
        return recursionFunc(modelArray, myStack, index+1);
    //When there is no ']' character, add the current index value to the Stack.
    }else{
      myStack.push(modelArray.get(index));
      return recursionFunc(modelArray, myStack, index+1);
    }                    
  }
}



