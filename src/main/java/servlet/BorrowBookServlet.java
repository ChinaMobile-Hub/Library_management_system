package servlet;

import bean.Book;
import bean.Reader;
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
import java.io.PrintWriter;

@WebServlet(name = "BorrowBookServlet")
public class BorrowBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookISBN = request.getParameter("bookISBN");
        Reader reader = (Reader) request.getSession().getAttribute("user");//��¼�Ķ�����Ϣ

        PrintWriter writer = response.getWriter();
        BookDao bookDao = new BookDao();
        ReaderBookDao readerBookDao = new ReaderBookDao();
        ReaderBookService readerBookService = new ReaderBookService();
        Book book = bookDao.get(Integer.parseInt(bookISBN));

        System.out.println(book);

        //�Խ��Ľ����ж�
        if (book==null) {
            writer.print(-1);//-1 û�и�ͼ��
            return;
        }
        if (book.getNum()<=0) {
            writer.print(-2);//-2 ͼ�鲻��
            return;
        }
        if (readerBookService.getByReaderAndBook(reader.getId(),Integer.parseInt(bookISBN))!=null) {
            writer.print(-3);//-3 �������ڽ��ĸ���
            return;
        }

        //�����ĵ��鼮����-1
        book.setNum(book.getNum()-1);
        bookDao.update(book);
        //��������Ϣ���鱾��Ϣ����reader_book
        ReaderBook readerBook = new ReaderBook();
        readerBook.setReader_id(reader.getId());
        readerBook.setBook_ISBN(Integer.parseInt(bookISBN));
        readerBookDao.add(readerBook);
        request.getRequestDispatcher("/borrowbook.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
