package todolistproject;

import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TodoApp {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) {
    	System.out.println("			<< To Do List >>");
    	System.out.println("	아래 나열되어 있는 항목 중 실행하고 싶은 항목의 번호를 입력해주세요.");

    	Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
         try {
        	System.out.println("----------------------------------------------------------------");
            System.out.println("			1. To Do 추가하기");
            System.out.println("			2. To Do 삭제하기");
            System.out.println("			3. 실행 여부 체크하기");
            System.out.println("			4. 전체 List 확인하기 ");
            System.out.println("			5. 카테고리별 TO DO 확인하기");
            System.out.println("			6. 프로그램 종료");
        	System.out.println("----------------------------------------------------------------");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTodo(scanner);
                    break;
                case 2:
                    removeTodo(scanner);
                    break;
                case 3:
                    markAsCompleted(scanner);
                    break;
                case 4:
                	System.out.println("------------------------------List------------------------------");
                    listAllTodos();
                    System.out.println();
                    break;
                case 5:
                    listTodosByCategory(scanner);
                    break;
                case 6:
                    running = false;
                    System.out.println("			프로그램이 종료되었습니다.");
                    break;
                default:
                    System.out.println("		*** 1 ~ 6번에 해당하는 번호를 재입력 해주세요 ***");
                    System.out.println();
            }
        } catch (InputMismatchException e) {
            System.out.println("번호를 입력해주세요.");
            System.out.println();
            scanner.nextLine(); // Clear the buffer
        }
        }
        scanner.close();
    }

    private static void addTodo(Scanner scanner) {
        System.out.print("타이틀 : ");
        String title = scanner.nextLine();
        System.out.print("상세 설명 : ");
        String description = scanner.nextLine();
        System.out.print("카테고리: ");
        String category = scanner.nextLine();

        todoList.addItem(title, description, category);
        System.out.println("나의 To Do 추가 성공.");
    }

    private static void removeTodo(Scanner scanner) {
        System.out.print("삭제하실 To Do의 타이틀을 입력해주세요: ");
        String title = scanner.nextLine();

        boolean removed = todoList.removeItem(title);
        if (removed) {
            System.out.println("To Do가 삭제되었습니다!");
        } else {
            System.out.println("작성하신 '" + title + "'(이)라는 제목을 찾을 수 없습니다.");
        }
    }

    private static void markAsCompleted(Scanner scanner) {
        System.out.print("To Do의 타이틀을 입력해주세요: ");
        String title = scanner.nextLine();
        boolean completed = todoList.markAsCompleted(title);
        if (completed) {
            System.out.println("To Do가 완료 처리 되었습니다!");
        } else {
            System.out.println("작성하신 '" + title + "'(이)라는 제목을 찾을 수 없습니다.");
            System.out.println("다시 시도해주세요");
        }
    }

    private static void listAllTodos() {
        List<TodoItem> items = todoList.getAllItems();
        if (items.isEmpty()) {
            System.out.println("			저장되어있는 LIST가 없습니다.");
        } else {
            for (TodoItem item : items) {
                System.out.println(item);
            }
        }
    }

    private static void listTodosByCategory(Scanner scanner) {
        System.out.print("카테고리를 입력하세요 : ");
        String category = scanner.nextLine();
        System.out.println("--------------------검색한 카테고리에 해당하는 List--------------------");
        List<TodoItem> items = todoList.getItemsByCategory(category);
        if (items.isEmpty()) {
            System.out.println("To Do에서 이 카테고리를 찾을 수 없습니다");
        } else {
            for (TodoItem item : items) {
                System.out.println(item);
            }
        }
    }   
  }


