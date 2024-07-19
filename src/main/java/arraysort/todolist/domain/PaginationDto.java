package arraysort.todolist.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class PaginationDto {

    private final int totalCount;                 // 총 리스트 개수
    private final int currentPage;                // 현재 페이지
    private final int rowCount;                   // 한 페이지 당 보여줄 리스트 개수
    private final int pageCount;                  // 페이지 블럭에 보여줄 페이지 개수
    private final boolean done;                   // 완료 페이지 여부
    private final List<TodoListDto> todoListDto;  // 페이지에 보여질 리스트

    private int startPage = 1;        // 블럭의 시작 페이지
    private int endPage;              // 블럭의 마지막 페이지
    private int totalPageCount;       // 총 페이지 개수
    private boolean isPrev = false;   // 다음 페이지 버튼 유무
    private boolean isNext = false;   // 이전 페이지 버튼 유무
    private int offset;               // 리스트를 얼마나 끊어줄지

    public PaginationDto(int totalCount, int currentPage, boolean done, List<TodoListDto> todoListDto) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.rowCount = 10;
        this.pageCount = 5;
        this.todoListDto = todoListDto;
        this.done = done;
        setPagination();
    }

    private void setPagination() {
        totalPageCount = (int) Math.ceil(totalCount * 1.0 / rowCount);
        startPage = startPage + (((currentPage - startPage) / pageCount) * pageCount);
        endPage = (Math.min((startPage - 1) + pageCount, totalPageCount));
        isPrev = ((currentPage * 1.0) / pageCount) > 1;
        isNext = endPage < totalPageCount;
        offset = (currentPage - 1) * rowCount;
    }
}
