import java.util.Arrays;

public class HitzgesJacobHillCipher
{

  public static int[][] findDecryptionKey(int encryptionKey[][])
  {
    
    int detA = ((encryptionKey[0][0] * encryptionKey[1][1]) - (encryptionKey[0][1] * encryptionKey[1][0]));
    while(detA < 0) {
      detA += 26;
    }
    
    
    
    //int det = detA % 26;
    //det * x = 1 mod 26
    int mNum = 1;
    while((detA * mNum) % 26 != 1) {
      mNum += 1;
    }
    

    int[][] returnArray = new int[2][2];
    
    returnArray[0][0] = encryptionKey [1][1];
    returnArray[1][1] = encryptionKey[0][0];
    returnArray[0][1] = -(encryptionKey[0][1]);
    returnArray[1][0] = -(encryptionKey[1][0]);
    
    while(returnArray[0][0] < 0) {
      returnArray[0][0] += 26;
    }
    while(returnArray[1][1] < 0) {
      returnArray[1][1] += 26;
    }
    while(returnArray[1][0] < 0) {
      returnArray[1][0] += 26;
    }
    while(returnArray[0][1] < 0) {
      returnArray[0][1] += 26;
    }
    
    returnArray[0][0] *= mNum;
    returnArray[1][0] *= mNum;
    returnArray[0][1] *= mNum;
    returnArray[1][1] *= mNum;
    
    returnArray[0][0] %= 26;
    returnArray[0][1] %= 26;
    returnArray[1][1] %= 26;
    returnArray[1][0] %= 26;
    
    System.out.println("Decryption Matrix: ");
    for (int i = 0; i < returnArray.length; i++) {
      for (int j = 0; j < returnArray[i].length; j++) {
        System.out.print(returnArray[i][j] + " ");
      }
      System.out.println();
    }
    
    return returnArray;
  }

  public static int[] encrypt(int plaintext[], int encryptionKey[][])
  {

    int[] returnArray;
    int k = 0;
    int actualLength = plaintext.length;
    if (plaintext.length % 2 == 0)
    {
      actualLength = plaintext.length;
      returnArray = new int[actualLength];
    }
    else
    {
      actualLength = plaintext.length + 1;
      returnArray = new int[actualLength];
      int[] temp = plaintext;
      plaintext = new int[plaintext.length + 1];
      int i = 0;
      for (i = 0; i < temp.length; i++) {
        plaintext[i] = temp[i];
      }
      plaintext[i] = 25;
    }
    for (int i = 0; i < actualLength + 2; i++)
    {
      if (i != 0 && i % 2 == 0) {
        int[] temp = new int[2];
        temp[0] = plaintext[i - 2];
        temp[1] = plaintext[i - 1];
        int[] encrypted = new int[2];
        encrypted[0] = ((encryptionKey[0][0] * temp[0]) + (encryptionKey[0][1] * temp[1]));
        while(encrypted[0] < 0) {
          encrypted[0] += 26;
        }
        encrypted[0] %= 26;
        encrypted[1] = ((encryptionKey[1][0] * temp[0]) + (encryptionKey[1][1] * temp[1])) % 26;
        while(encrypted[1] < 0) {
          encrypted[1] += 26;
        }
        encrypted[1] %= 26;
        returnArray[k] = encrypted[0];
        k++;
        returnArray[k] = encrypted[1];
        k++;
      }
    }

    System.out.println("Encrypted Text: ");
    System.out.println(numToChar(returnArray));
    return returnArray;
  }

  public static int[] decrypt(int ciphertext[], int decryptionKey[][])
  {
    int[] returnArray = new int[ciphertext.length];
    int k = 0;
    for (int i = 0; i < ciphertext.length + 2; i++)
    {
      if (i != 0 && i % 2 == 0) {
        int[] temp = new int[2];
        temp[0] = ciphertext[i - 2];
        temp[1] = ciphertext[i - 1];
        int[] decrypted = new int[2];
        decrypted[0] = ((decryptionKey[0][0] * temp[0]) + (decryptionKey[0][1] * temp[1])) % 26;
        decrypted[1] = ((decryptionKey[1][0] * temp[0]) + (decryptionKey[1][1] * temp[1])) % 26;
        returnArray[k] = decrypted[0];
        k++;
        returnArray[k] = decrypted[1];
        k++;
      }
    }

    System.out.println("Decrypted Text: ");
    System.out.println(numToChar(returnArray));
    return returnArray;
  }

  public static String numToChar(int[] nums)
  {
    int numsL = nums.length;
    char[] returnChars = new char[numsL];
    for (int i = 0; i < nums.length; i++)
    {
      int keyNumber = nums[i];
      char keyLetter = 'A';

      switch (keyNumber + 1)
      {
        case 1:
          keyNumber = 0;
          keyLetter = 'A';
          returnChars[i] = keyLetter;
          break;
        case 2:
          keyNumber = 1;
          keyLetter = 'B';
          returnChars[i] = keyLetter;
          break;
        case 3:
          keyNumber = 2;
          keyLetter = 'C';
          returnChars[i] = keyLetter;
          break;
        case 4:
          keyNumber = 3;
          keyLetter = 'D';
          returnChars[i] = keyLetter;
          break;
        case 5:
          keyNumber = 4;
          keyLetter = 'E';
          returnChars[i] = keyLetter;
          break;
        case 6:
          keyNumber = 5;
          keyLetter = 'F';
          returnChars[i] = keyLetter;
          break;
        case 7:
          keyNumber = 6;
          keyLetter = 'G';
          returnChars[i] = keyLetter;
          break;
        case 8:
          keyNumber = 7;
          keyLetter = 'H';
          returnChars[i] = keyLetter;
          break;
        case 9:
          keyNumber = 8;
          keyLetter = 'I';
          returnChars[i] = keyLetter;
          break;
        case 10:
          keyNumber = 9;
          keyLetter = 'J';
          returnChars[i] = keyLetter;
          break;
        case 11:
          keyNumber = 10;
          keyLetter = 'K';
          returnChars[i] = keyLetter;
          break;
        case 12:
          keyNumber = 11;
          keyLetter = 'L';
          returnChars[i] = keyLetter;
          break;
        case 13:
          keyNumber = 12;
          keyLetter = 'M';
          returnChars[i] = keyLetter;
          break;
        case 14:
          keyNumber = 13;
          keyLetter = 'N';
          returnChars[i] = keyLetter;
          break;
        case 15:
          keyNumber = 14;
          keyLetter = 'O';
          returnChars[i] = keyLetter;
          break;
        case 16:
          keyNumber = 15;
          keyLetter = 'P';
          returnChars[i] = keyLetter;
          break;
        case 17:
          keyNumber = 16;
          keyLetter = 'Q';
          returnChars[i] = keyLetter;
          break;
        case 18:
          keyNumber = 17;
          keyLetter = 'R';
          returnChars[i] = keyLetter;
          break;
        case 19:
          keyNumber = 18;
          keyLetter = 'S';
          returnChars[i] = keyLetter;
          break;
        case 20:
          keyNumber = 19;
          keyLetter = 'T';
          returnChars[i] = keyLetter;
          break;
        case 21:
          keyNumber = 20;
          keyLetter = 'U';
          returnChars[i] = keyLetter;
          break;
        case 22:
          keyNumber = 21;
          keyLetter = 'V';
          returnChars[i] = keyLetter;
          break;
        case 23:
          keyNumber = 22;
          keyLetter = 'W';
          returnChars[i] = keyLetter;
          break;
        case 24:
          keyNumber = 23;
          keyLetter = 'X';
          returnChars[i] = keyLetter;
          break;
        case 25:
          keyNumber = 24;
          keyLetter = 'Y';
          returnChars[i] = keyLetter;
          break;
        case 26:
          keyNumber = 25;
          keyLetter = 'Z';
          returnChars[i] = keyLetter;
          break;
      }
    }

    String returnString = new String(returnChars);
    return returnString;

  }

  public static void main(String[] args)
  {
    int[][] encryptionKey = new int[2][2];
    encryptionKey[0][0] = 16;
    encryptionKey[0][1] = 7;
    encryptionKey[1][0] = 9;
    encryptionKey[1][1] = 14;

    int[] plainText = new int[11];
    plainText[0] = 9;
    plainText[1] = 12;
    plainText[2] = 20;
    plainText[3] = 2;
    plainText[4] = 18;
    plainText[5] = 8;
    plainText[6] = 18;
    plainText[7] = 2;
    plainText[8] = 14;
    plainText[9] = 14;
    plainText[10] = 11;
    
    int[] cypherText2 = new int[2];
    cypherText2[0] = 'D';
    cypherText2[1] = 'L';

    int[][] decryptionKey = findDecryptionKey(encryptionKey);
    int[] cypherText = encrypt(plainText, encryptionKey);
    decrypt(cypherText, decryptionKey);
    decrypt(cypherText2, decryptionKey);
  }

}
