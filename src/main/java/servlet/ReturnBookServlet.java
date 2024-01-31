package servlet;

import bean.Book;
import bean.Reader;
import dao.BookDao;
import service.ReaderBookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookISBN = Integer.parseInt(request.getParameter("bookISBN"));
        Reader reader = (Reader) request.getSession().getAttribute("user");

        //��reader_book���еĶ�Ӧ��¼ɾ��
        ReaderBookService readerBookService = new ReaderBookService();
        readerBookService.deleteByReaderAndBook(reader.getId(),bookISBN);

        //����Ӧbook����Ŀ��һ
        BookDao bookDao = new BookDao();
        Book book = bookDao.get(bookISBN);
        book.setNum(book.getNum()+1);
        bookDao.update(book);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
