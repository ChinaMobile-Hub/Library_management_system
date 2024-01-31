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

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ͨ��writer�������ͬ��ֵ�������ajax�ص�������data��ֵ
        //ͨ�����ֵ��ajax���������ж�
        PrintWriter writer = response.getWriter();
        String username = request.getParameter("username");
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");//����ǹ���Ա���Ƕ���

        //��֤�������Ϣ����
        //��������������һ��û�����룬������ʾ������Ϣ������
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(userid) || StringUtil.isEmpty(password)) {
            writer.print(0);
            return;
        }
        if ("reader".equals(identity)) {//�����ѧ��ע��

            ReaderDao ReaderDao = new ReaderDao();
            Reader Reader=null;
            try {
                Integer.parseInt(userid);
            } catch (Exception e) {
                e.printStackTrace();
                writer.print(-1);//������˺Ų��Ǵ�����
                return;
            }
            Reader = ReaderDao.get(Integer.parseInt(userid));

            if (!(Reader==null)) {
                writer.print(-2);//���ߴ���
                return;
            }
            Reader=new Reader();
            Reader.setId(Integer.parseInt(userid));
            Reader.setPassword(password);
            Reader.setName(username);
            System.out.println(Reader.toString());
            ReaderDao.add(Reader);
            writer.print(-3);//ע��ɹ�
            return;
        } else if ("admin".equals(identity)) {//�������ʦע��
            AdminDao AdminDao = new AdminDao();
            Admin Admin=null;
            try {
                Integer.parseInt(userid);
            } catch (Exception e) {
                e.printStackTrace();
                writer.print(-1);//������˺Ų��Ǵ�����
                return;
            }
            Admin = AdminDao.get(Integer.parseInt(userid));

            if (!(Admin==null)) {
                writer.print(-2);//����Ա���ڴ���
                return;
            }
            Admin=new Admin();
            Admin.setId(Integer.parseInt(userid));
            Admin.setPassword(password);
            Admin.setName(username);
            System.out.println(Admin.toString());
            AdminDao.add(Admin);
            writer.print(-3);//ע��ɹ�
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
