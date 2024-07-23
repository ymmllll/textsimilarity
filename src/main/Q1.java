package main;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;
public class Q1{

    public static double CodeFile(String path1,String path2) {
        try {
            // 读取文件1的内容
            String file1Content = readFileContent(path1);
            // 读取文件2的内容
            String file2Content = readFileContent(path2);
            // 计算文件相似度
            double similarity = calculateSimilarity(file1Content, file2Content);
            System.out.println("文件相似度: " + similarity);
            return similarity;
            //ClosestCodeMatch();
            //ClosestCodeMatches();
            //closestCodes("C:\\Users\\阳佳琦\\Desktop\\lab1-codes-fortest", ".*\\.java", 0, false);
            
        } catch (IOException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    // 读取文件内容
    private static String readFileContent(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        // 使用BufferedReader逐行读取文件内容
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        return content.toString();
    }

    // 计算文件相似度
    private static double calculateSimilarity(String content1, String content2) {
        // 获取文本内容的词频向量
        Map<String, Integer> vector1 = getTermFrequency(content1);
        Map<String, Integer> vector2 = getTermFrequency(content2);

        // 计算向量的点积
        double dotProduct = calculateDotProduct(vector1, vector2);
        // 计算向量的模
        double magnitude1 = calculateMagnitude(vector1);
        double magnitude2 = calculateMagnitude(vector2);

        // 计算相似度
        return dotProduct / (magnitude1 * magnitude2);
    }

    // 计算词频
    private static Map<String, Integer> getTermFrequency(String content) {
        Map<String, Integer> termFrequency = new HashMap<>();
        // 将文本内容按空白字符分割为单词数组
        String[] words = content.split("\\s+");

        // 统计每个单词的出现频率
        for (String word : words) {
            termFrequency.put(word, termFrequency.getOrDefault(word, 0) + 1);
        }

        return termFrequency;
    }

    // 计算向量的点积
    private static double calculateDotProduct(Map<String, Integer> vector1, Map<String, Integer> vector2) {
        double dotProduct = 0;

        // 遍历较小的向量
        Map<String, Integer> smallerVector = vector1.size() < vector2.size() ? vector1 : vector2;
        Map<String, Integer> largerVector = vector1.size() >= vector2.size() ? vector1 : vector2;

        // 计算点积
        for (Map.Entry<String, Integer> entry : smallerVector.entrySet()) {
            String term = entry.getKey();
            int frequency1 = entry.getValue();
            int frequency2 = largerVector.getOrDefault(term, 0);

            dotProduct += frequency1 * frequency2;
        }

        return dotProduct;
    }

    // 计算向量的模
    private static double calculateMagnitude(Map<String, Integer> vector) {
        double magnitudeSquared = 0;

        // 计算向量模的平方
        for (int frequency : vector.values()) {
            magnitudeSquared += frequency * frequency;
        }

        // 对模的平方进行开方，得到模的值
        return Math.sqrt(magnitudeSquared);
    }
    public static String ClosestCodeMatch(String gui2_str) {
    	 ArrayList<String> strings = new ArrayList<>();
    	 scanUrl scanner=new scanUrl(gui2_str);
    	 strings=scanner.get_strings();
    	 int n=0; 
    	 for(int i=strings.size();i>=1;i--)n+=i;
    	 double[] temp=new double[n];
    	 int index=0;
    	 double max_similarity=0.0;
    	 String s=null;
 		 String t=null;
    	 for(int i=0;i<strings.size()-1;i++) {
    		 for(int j=i+1;j<strings.size();j++) {
    		// 读取文件1的内容
    		String str1= strings.get(i);
    		String str2=strings.get(j);
    		 try {
    	            // 读取文件1的内容
    	            String file1Content = readFileContent(str1);
    	            // 读取文件2的内容
    	            String file2Content = readFileContent(str2);
    	            // 计算文件相似度
    	            double similarity = calculateSimilarity(file1Content, file2Content);
    	            temp[index++]=similarity;
    	            //System.out.println("文件相似度: " + similarity);
    	            if(similarity>max_similarity) {
    	            	max_similarity=similarity;
    	            	s=str1;t=str2;
    	            }
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }
    	   }
    	 }
    	 System.out.println("最相似的文件是："+s+" "+t);
    	 System.out.println("相似度为："+max_similarity);
    	 String str="最相似的文件是："+s+" "+t+"\n"+"相似度为："+max_similarity;
    	 return str;
    }
    public static void ClosestCodeMatches(String line,ArrayList<String> list1) {
    	 ArrayList<String> strings = new ArrayList<>();
    	 scanUrl scanner=new scanUrl(line);
    	 strings=scanner.get_strings();
    	 int n=0; 
    	 for(int i=strings.size()-1;i>=1;i--)n+=i;
    	 double[][] temp=new double[strings.size()][strings.size()];
    	 double max_similarity=0.0;
    	 int t=0;
 		 for(int i=0;i<strings.size();i++)temp[i][i]=0;
    	 for(int i=0;i<strings.size()-1;i++) {
    		 for(int j=i+1;j<strings.size();j++) {
    		// 读取文件1的内容
    		String str1= strings.get(i);
    		String str2=strings.get(j);
    		 try {
    	            // 读取文件1的内容
    	            String file1Content = readFileContent(str1);
    	            // 读取文件2的内容
    	            String file2Content = readFileContent(str2);
    	            // 计算文件相似度
    	            double similarity = calculateSimilarity(file1Content, file2Content);
    	            //System.out.println("文件相似度: " + similarity
    	            temp[i][j]=temp[j][i]=similarity;
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }
    	   }
    	 }
    	 for(int i=0;i<strings.size();i++) {
    		 for(int j=0;j<strings.size();j++) {
    			 if(temp[i][j]>max_similarity) {
    				 max_similarity=temp[i][j];
    				 t=j;
    			 }
    		 }
    		 System.out.println("最大相似度："+max_similarity);
    		 System.out.println(strings.get(i)+"文件相似度最高的是:"+strings.get(t));
    		 list1.add("最大相似度："+max_similarity+"\n"+strings.get(i)+"文件相似度最高的是："+strings.get(t)+"\n");
    		 max_similarity=0;t=0;
    	 }
    }
    
    /**
	 * 用于评价各相关目录下，指定文件的相似性。
	 * Similarity   子目录1                        子目录2
	 * 100%        Java课内实习-202110001234-xxx-实习1     Java课内实习-202110001235-xxx-实习1
	 * 89%         Java课内实习-202110001234-xxx-实习1     Java课内实习-202110001236-xxx-实习1
	 * ....
	 * @param path 作业文件所在的目录，比如这里是：codes/lab1
	 * @param fileNameMatches：用来过滤进行比较的文件名的文件名或者正则表达式.
	 * 如 "DrawableTurtle.java"，"*.java","turtle/*.java"
	 * 如果一个子目录下有多个符合条件的文件，将多个文件合并成一个文件。
	 * 
	 * @param topRate:取值范围从[0,100],输出控制的阈值
	 * 	从高往低输出高于topRate%相似列表，如
	 * @param removeComments:是否移除注释内容	
	 * 	 */

	 public static void closestCodes(String path, String fileNameMatches, double topRate, boolean removeComments,ArrayList<String> list1,ArrayList<String> list2,double[] x) 
			 throws IOException {
	        // Obtain all subdirectories
	        List<Path> subDirs = Files.walk(Paths.get(path), 1)
	                                  .filter(Files::isDirectory)
	                                  .collect(Collectors.toList());
	        subDirs.remove(0); // Remove the root directory from the list
	        
	        // Map to hold combined file contents for each subdirectory
	        Map<Path, String> combinedContents = new HashMap<>();

	        // Prepare regex pattern for file matching
	        Pattern pattern = Pattern.compile(fileNameMatches.replace("*", ".*").replace("?", ".?"));

	        // Combine files and filter by file name pattern
	        for (Path dir : subDirs) {
	            StringBuilder combined = new StringBuilder();
	            Files.walk(dir)
	                 .filter(Files::isRegularFile)
	                 .filter(p -> pattern.matcher(p.getFileName().toString()).matches())
	                 .forEach(p -> {
	                     try {
	                         String content = new String(Files.readAllBytes(p));
	                         if (removeComments) {
	                             content = content.replaceAll("//.*|/\\*(.|\n)*?\\*/", ""); // Simple comment remover
	                         }
	                         combined.append(content);
	                     } catch (IOException e) {
	                         e.printStackTrace();
	                     }
	                 });
	            combinedContents.put(dir, combined.toString());
	        }

	        // Calculate and print similarities
	        List<Path> dirs = new ArrayList<>(combinedContents.keySet());
	        int index=0;
	        for (int i = 0; i < dirs.size(); i++) {
	            for (int j = i + 1; j < dirs.size(); j++) {
	                double similarity = calculateSimilarity(combinedContents.get(dirs.get(i)), combinedContents.get(dirs.get(j)));
	                System.out.println(similarity);
	                if (similarity >= topRate) {
	                    System.out.printf("%.0f%% Similarity between %s and %s\n", similarity, dirs.get(i), dirs.get(j));
	                    x[index]=similarity;
	                    System.out.println(x[index]);
	                    Path path1=dirs.get(i);
	                    Path path2=dirs.get(j);
	                    list1.add(path1.toString());
	                    list2.add(path2.toString());
	                    index++;
	                }
	            }
	        }
	    }

}