package todoApp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

public class todoToImg {

//    public static void main(String[] args) {
//    	TodoToImg();
//    	DoneToImg();
//    }

    public static void TodoToImg(String user) {
        String txtFilePath = "src/todoApp/" + user + "/todoList.txt";
        String imageFilePath = "src/todoApp/" + user + "/todoList.jpg";
        
        String backgroundImagePath = "src/todoApp/asset/bg.jpg";
        
        try {
        	// .txt 파일 읽기
            File txtFile = new File(txtFilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(txtFile), StandardCharsets.UTF_8));

            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append(System.lineSeparator());
            }
            reader.close();
            String content = contentBuilder.toString();

            // 배경 이미지 불러오기
            File backgroundImageFile = new File(backgroundImagePath);
            BufferedImage backgroundImage = ImageIO.read(backgroundImageFile);
            
            // 이미지 크기 설정
            int width = calculateImageWidth(content);
            int height = calculateImageHeight(content);

            // 이미지 생성
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            
            // 배경 이미지 그리기
            g2d.drawImage(backgroundImage, 0, 0, null);
            
            // 텍스트 설정
            Font font = new Font("NanumGothic", Font.PLAIN, 20);
            
            g2d.setFont(font);
            g2d.setColor(Color.BLACK);

            // 텍스트 그리기
            FontMetrics fontMetrics = g2d.getFontMetrics();
            String[] lines = content.split("\n");
            int lineHeight = fontMetrics.getHeight();
            int y = lineHeight;
            for (String currentLine : lines) {
                g2d.drawString(currentLine, 10, y);
                y += lineHeight;
            }

            // 이미지 파일로 저장
            File imageFile = new File(imageFilePath);
            ImageIO.write(image, "jpg", imageFile);

            System.out.println(user + "의 todoList를 이미지로 변환 완료..");
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println(user + "에 todoList가 업습니다." );
        }

    }
    
    public static void DoneToImg(String user) {
        String txtFilePath = "src/todoApp/" + user + "/doneList.txt";
        String imageFilePath = "src/todoApp/" + user + "/doneList.jpg";
        
        String backgroundImagePath = "src/todoApp/asset/bg.jpg";
        
        try {
        	// .txt 파일 읽기
            File txtFile = new File(txtFilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(txtFile), StandardCharsets.UTF_8));

            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append(System.lineSeparator());
            }
            reader.close();
            String content = contentBuilder.toString();

            // 배경 이미지 불러오기
            File backgroundImageFile = new File(backgroundImagePath);
            BufferedImage backgroundImage = ImageIO.read(backgroundImageFile);
            
            // 이미지 크기 설정
            int width = calculateImageWidth(content);
            int height = calculateImageHeight(content);

            // 이미지 생성
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            
            // 배경 이미지 그리기
            g2d.drawImage(backgroundImage, 0, 0, null);
            
            // 텍스트 설정
            Font font = new Font("NanumGothic", Font.PLAIN, 20);
            
            g2d.setFont(font);
            g2d.setColor(Color.BLACK);

            // 텍스트 그리기
            FontMetrics fontMetrics = g2d.getFontMetrics();
            String[] lines = content.split("\n");
            int lineHeight = fontMetrics.getHeight();
            int y = lineHeight;
            for (String currentLine : lines) {
                g2d.drawString(currentLine, 10, y);
                y += lineHeight;
            }

            // 이미지 파일로 저장
            File imageFile = new File(imageFilePath);
            ImageIO.write(image, "jpg", imageFile);

            System.out.println(user + "의 doneList를 이미지로 변환 완료..");
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println(user + "에 todoList가 업습니다." );
        }

    }
    

    private static int calculateImageWidth(String content) {
        Font font = new Font("Arial", Font.PLAIN, 21);
        FontMetrics fontMetrics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics().getFontMetrics(font);
        String[] lines = content.split("\n");
        int maxWidth = 0;
        for (String line : lines) {
            int width = fontMetrics.stringWidth(line);
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth + 20; // 여백을 위해 20을 추가
    }

    private static int calculateImageHeight(String content) {
        Font font = new Font("Arial", Font.PLAIN, 20);
        FontMetrics fontMetrics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics().getFontMetrics(font);
        String[] lines = content.split("\n");
        int lineHeight = fontMetrics.getHeight();
        return (lines.length * lineHeight) + 20; // 여백을 위해 20을 추가
    }
}