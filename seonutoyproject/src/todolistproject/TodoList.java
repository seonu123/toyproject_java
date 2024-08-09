package todolistproject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TodoList {
    private List<TodoItem> items = new ArrayList<>();
    private final String filePath  = "ToDoList.txt";
    
    public TodoList() {
    	clearFile();	
    }

    // 새로운 todo 추가 메서드
    public void addItem(String title, String description, String category) {
        TodoItem item = new TodoItem(title, description, category);	// 이 부분 수정해야할 듯
        items.add(item);
        saveToFile();
    }
    
    // 타이틀로 todo 삭제 메서드
    public boolean removeItem(String title) {	
        boolean removed = items.removeIf(item -> item.getTitle().equalsIgnoreCase(title));
        if (removed) {
           saveToFile();
        }
        return removed;
    }
    
    // 타이틀로 todo 완료 여부 체크
    public boolean markAsCompleted(String title) {	 // To Do 실행 여부 선택 시 제목 입력하는 부분
        boolean completed = items.stream()	// 스트림 생성
                .filter(item -> item.getTitle().equalsIgnoreCase(title))	// 스트림의 각 item 검사 -> 제목이 타이틀과 일치하는 항목만 걸러냄.  
                .findFirst() 
                         .map(item -> {	// 찾은 거에 대한 해당 항목 완료 처리 파일 저장
                    item.setCompleted(true);
                    saveToFile();
                    return true;
                })
                .orElse(false);
        return completed;
    }

    // 모든 리스트 반환 메서드
    public List<TodoItem> getAllItems() {
        return new ArrayList<>(items);
    }

    // 카테고리에 해당하는 todo 반환 메서드
    // 람다식 + 스트림 
    public List<TodoItem> getItemsByCategory(String category) {
        return items.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))	// filter : 스트림 중간연산
                .collect(Collectors.toList());	// collect : 스트림 최종연산
    }
    
    // 간단하게 코드를 짤 수 있어서 람다식, 스트림 사용 안 했음.
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (TodoItem item : items) {
                writer.write(String.join(" | ",
                        item.getTitle(),
                        item.getDescription(),
                        item.getCategory(),
                        Boolean.toString(item.isCompleted())
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 초기화
    private void clearFile() {
    	try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
    		writer.write(""); 	// 파일 내용 비움
    	} catch(IOException e) {
    		e.printStackTrace();
    		}	
    }
}


