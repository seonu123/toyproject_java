package todolistproject;


public class TodoItem {
    private String title;			// 제목
    private String description;		// 설명
    private String category;		// 카테고리	
    private boolean isCompleted;	// 완료 여부

    // 생성자 (제목, 설명, 카테고리 사용해서 TodoItem 객체 초기화
    public TodoItem(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.isCompleted = false; // 기본적으로 새 아이템은 완료되지 않음
        this.category = category;
    }


  
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isCompleted() {
        return isCompleted;
    }

  
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

  
    public String getCategory() {
        return category;
    }

   
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("TITLE : %s | 내용 : %s | 카테고리 : %s | 완료 : %s",
                title, description, category, isCompleted);
    }
}
