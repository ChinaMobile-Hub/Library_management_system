package servlet;

import bean.Admin;
import bean.Reader;
import dao.AdminDao;
import dao.ReaderDao;
import utils.StringUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");//����ǹ���Ա���Ƕ���
        PrintWriter writer = response.getWriter();//������ajax�Ļص�������������
        if (StringUtil.isEmpty(userid) || StringUtil.isEmpty(password)) {//����������ϢΪ��
            writer.print(-1);//-1 ��ϢΪ��
            return;
        }
        if (!StringUtil.isInteger(userid)) {
            writer.print(-2);//-2 ������˺Ų��Ǵ�����
            return;
        }

        if ("admin".equals(identity)) {//����Ա
            AdminDao AdminDao = new AdminDao();
            Admin Admin=null;
            Admin=AdminDao.get(Integer.parseInt(userid));
            if (Admin==null) {
                writer.print(-3);//-3 ���޴���
                return;
            }
            if (!(Admin.getPassword().equals(password))) {
                writer.print(-4);//-4 �������
                return;
            }
            //��һ���ж�������Ҫ��session�����õ�¼�������Ϣ
            request.getSession().setAttribute("user",Admin);
            request.getSession().setAttribute("username",Admin.getName());
        } else if ("reader".equals(identity)) {
            ReaderDao ReaderDao = new ReaderDao();
            Reader Reader=null;
            Reader=ReaderDao.get(Integer.parseInt(userid));
            if (Reader==null) {
                writer.print(-3);//-3 ���޴���
                return;
            }
            if (!(Reader.getPassword().equals(password))) {
                writer.print(-4);//-4 �������
                return;
            }
            //��session�������û��������Ϣ
            request.getSession().setAttribute("user",Reader);
            request.getSession().setAttribute("username",Reader.getName());
        }
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
