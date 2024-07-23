package main;
import java.util.Scanner;
import java.util.ArrayList;

public class scanUrl{
	private ArrayList<String> strings;
	public ArrayList<String> get_strings(){
		return this.strings;
	}
	public scanUrl(String line) {
		this.strings=new ArrayList<>();
		scan(line);
	}
    public void scan(String line) {

        // 使用空格将输入的字符串分割
        String[] inputs = line.split("\\s+");

        // 将分割后的字符串添加到 ArrayList 中
        for (String str : inputs) {
            this.strings.add(str);
        }

    }
}
