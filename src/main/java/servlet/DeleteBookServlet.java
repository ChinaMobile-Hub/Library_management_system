package servlet;

import bean.ReaderBook;
import dao.BookDao;
import dao.ReaderBookDao;
import service.ReaderBookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookISBN = Integer.parseInt(request.getParameter("bookISBN"));
        BookDao bookDao = new BookDao();
        bookDao.delete(bookISBN);

        //删除该书以后，如果有读者还在借阅该书，要将该记录删除
        ReaderBookService readerBookService = new ReaderBookService();
        readerBookService.deleteByBook(bookISBN);

        request.getRequestDispatcher("/managebook.jsp").forward(request,response);
    }
}
