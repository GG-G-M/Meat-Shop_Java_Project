
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.IOException;
import java.io.*;
import javax.swing.JOptionPane;

public class Data {
    
   
    
    //--Print Reciept--
    public void PrintReceipt() throws IOException{
    BufferedWriter writer = new BufferedWriter(new FileWriter("Receipt.txt"));
    writer.write(
            "_____Receipt_____"+
             "\n Total User Order: " + this.CurrentlyInCart()+
             "\n Pig Belly: " + this.GetUserOrder(1) +
             "\n Pig Ham: " + this.GetUserOrder(2) +
             "\n Pig Ribs: " + this.GetUserOrder(3) +
             "\n Cow Brisket: " + this.GetUserOrder(4) +
             "\n Cow Loin: " + this.GetUserOrder(5) +
             "\n Cow Ribs: " + this.GetUserOrder(6) +            
             "\n Chicken Breast: " + this.GetUserOrder(7) +
             "\n Chicken Legs: " + this.GetUserOrder(8) +
             "\n Chicken Wings: " + this.GetUserOrder(9)            
            
    );
    writer.close();
    }
    public void ReadReciept(){
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("Receipt.txt"));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
    }
    public void ResetOrder()throws IOException{
        
        for (int i = 1; i < 10 ; i++){
            this.ResetOrder01("0", i);
        }
    }
    public void ResetOrder01(String data, int line) throws IOException{
        int Line = line;
    Path FILE_PATH = Path.of("UserOrder.txt");
    List<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8));
    fileContent.set(Line-1, data);
    Files.write(FILE_PATH, fileContent, StandardCharsets.UTF_8);
    }
    
    
    //@@@@@___SET DATA___@@@@@
    public void SetData(String data, int line ) throws IOException{
    int Line = line;
    Path FILE_PATH = Path.of("Data.txt");
    List<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8));
    fileContent.set(Line-1, data);
    Files.write(FILE_PATH, fileContent, StandardCharsets.UTF_8);
    }
    public int GetData(int line) throws IOException{
        int data = Integer.parseInt(Files.readAllLines(Paths.get("Data.txt")).get(line-1));
        return data;
    }
    
    //@@@@@___SET USERORDER@@@@@
    public int GetUserMoney() throws IOException{
        int data = Integer.parseInt(Files.readAllLines(Paths.get("UserInfo.txt")).get(1-1));
        return data;
    }  
    public void SetUserMoney(String money) throws IOException{
    
    Path FILE_PATH = Path.of("UserInfo.txt");
    List<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8));
    fileContent.set(0, money);
    Files.write(FILE_PATH, fileContent, StandardCharsets.UTF_8);
    }
    public void SetUserOrder(String data, int line ) throws IOException{
    int a = this.GetUserOrder(line);
    int b = Integer.parseInt(data);
    String result = Integer.toString(a+b);
    int Line = line;
    Path FILE_PATH = Path.of("UserOrder.txt");
    List<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8));
    fileContent.set(Line-1, result);
    Files.write(FILE_PATH, fileContent, StandardCharsets.UTF_8);
    }
     public int GetUserOrder(int line) throws IOException{
        int data = Integer.parseInt(Files.readAllLines(Paths.get("UserOrder.txt")).get(line-1));
        return data;
    }
     //@@@@@___CART@@@@@
     public int CurrentlyInCart() throws IOException{
        int cart = 0, cartadd;
        
        try (BufferedReader br = new BufferedReader(new FileReader("UserOrder.txt"))) {
        String line;
        
        while ((line = br.readLine()) != null) {
           cartadd = Integer.parseInt(line);
           cart += cartadd;
           
    }
} 
        return cart;
    }
     //@@@@@___UpdateStock____@@@@@
      public void UpdateStocks(String order, int line ) throws IOException {
      int a = this.GetData(line);
      int b = Integer.parseInt(order);
      
      int result = a - b;
              this.SetData(Integer.toString(result), line);
      }
     
      public void Incorrect(){    JOptionPane.showMessageDialog(null, "Field is Empty");    }
      public void NoStocksLeft(){          JOptionPane.showMessageDialog(null, "No Stocks Left");      }
      public void NoMoneyLeft(){          JOptionPane.showMessageDialog(null, "Not Enough Money");      }
}
