import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

import java.util.ArrayList;

/**
 * Servlet implementation class LibController
 */
@WebServlet("/LibController")
public class LibController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibRepo librepo;

	/**
	 * Default constructor.
	 */
	public LibController() {
		super();
		librepo = new LibRepo();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "index.jsp";
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String out = "";
		if (request.getParameter("connect") != null) {
			try {
				librepo.buildTables();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out = "Connected to Database!";
			request.setAttribute("final", out);
			RequestDispatcher view = request.getRequestDispatcher("content/homepage.jsp");
			view.include(request, response);
		} else if (request.getParameter("disconnect") != null) {
			try {
				librepo.dropTables();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out = "Database dropped!";
			request.setAttribute("final", out);
			RequestDispatcher view = request.getRequestDispatcher("content/homepage.jsp");
			view.include(request, response);
		} else {
			String action = request.getParameter("action");
			String v1 = request.getParameter("v1");
			String v2 = request.getParameter("v2");
			String v3 = request.getParameter("v3");
			String v4 = request.getParameter("v4");
			switch (action) {
			case "0":
				out = "You have not selected an option. Please select an option.";
				request.setAttribute("final", out);
				RequestDispatcher view = request.getRequestDispatcher("content/homepage.jsp");
				view.include(request, response);
				break;
			case "1":
				if (v1.equals("") || v2.equals("") || v3.equals("") || v4.equals("")) {
					out = "You have not entered enough information. Please retry.";
				} else {
					boolean result = librepo.newUser(v1, v2, v3, v4);
					if (result) {
						out = "User Registration successful!";
					} else {
						out = "User ID is already in use. Please choose another and retry.";
					}
				}
				request.setAttribute("final", out);
				view = request.getRequestDispatcher("content/homepage.jsp");
				view.include(request, response);
				break;
			case "2":
				if (v1.equals("") || v2.equals("")) {
					out = "You have not entered enough information. Please retry.";
				} else {
					boolean result = librepo.newLibrarian(v1, v2);
					if (result) {
						out = "Librarian Registration successful!";
					} else {
						out = "Librarian ID is already in use. Please choose another and retry.";
					}
				}
				request.setAttribute("final", out);
				view = request.getRequestDispatcher("content/homepage.jsp");
				view.include(request, response);
				break;
			case "3":
				if (v1.equals("") || v2.equals("")) {
					out = "You have not entered enough information. Please retry.";
				} else {
					boolean result = librepo.newVolunteer(v1, v2);
					if (result) {
						out = "Volunteer Registration successful!";
					} else {
						out = "Volunteer ID is already in use. Please choose another and retry.";
					}
				}
				request.setAttribute("final", out);
				view = request.getRequestDispatcher("content/homepage.jsp");
				view.include(request, response);
				break;
			case "4":
				if (v1.equals("") || v2.equals("")) {
					out = "You have not entered enough information. Please retry.";
				} else {
					boolean result = librepo.newStudyRoom(v1, v2);
					if (result) {
						out = "Study Room Registration successful!";
					} else {
						out = "Room # is already in use. Please choose another and retry.";
					}
				}
				request.setAttribute("final", out);
				view = request.getRequestDispatcher("content/homepage.jsp");
				view.include(request, response);
				break;
			case "5":
				if (v1.equals("") || v2.equals("")) {
					out = "You have not entered enough information. Please retry.";
				} else {
					boolean result = librepo.newLibraryCard(v1, v2);
					if (result) {
						out = "Library Card Registration successful!";
					} else {
						out = "Librarian Card ID is already in use. Please choose another and retry.";
					}
				}
				request.setAttribute("final", out);
				view = request.getRequestDispatcher("content/homepage.jsp");
				view.include(request, response);
				break;
			case "6":
				if (v1.equals("") || v2.equals("")) {
					out = "You have not entered enough information. Please retry.";
				} else {
					boolean result = librepo.newPublishingHouse(v1, v2);
					if (result) {
						out = "Publisher Registration successful!";
					} else {
						out = "Publisher ID is already in use. Please choose another and retry.";
					}
				}
				request.setAttribute("final", out);
				view = request.getRequestDispatcher("content/homepage.jsp");
				view.include(request, response);
				break;
			case "7":
				if (v1.equals("") || v2.equals("") || v3.equals("")) {
					out = "You have not entered enough information. Please retry.";
				} else {
					boolean result = librepo.newDVD(v1, v2, v3);
					if (result) {
						out = "DVD Registration successful!";
					} else {
						out = "DVD ID is already in use. Please choose another and retry.";
					}
				}
				request.setAttribute("final", out);
				view = request.getRequestDispatcher("content/homepage.jsp");
				view.include(request, response);
				break;
			case "8":
				if (v1.equals("") || v2.equals("") || v3.equals("")) {
					out = "You have not entered enough information. Please retry.";
				} else {
					boolean result = librepo.newBook(v1, v2, v3);
					if (result) {
						out = "Book Registration successful!";
					} else {
						out = "Book ID is already in use. Please choose another and retry.";
					}
				}
				request.setAttribute("final", out);
				view = request.getRequestDispatcher("content/homepage.jsp");
				view.include(request, response);
				break;
			case "9":
				if (v1.equals("") || v2.equals("") || v3.equals("")) {
					out = "You have not entered enough information. Please retry.";
				} else {
					boolean result = librepo.newCategory(v1, v2, v3);
					if (result) {
						out = "Category Registration successful!";
					} else {
						out = "Category ID is already in use. Please choose another and retry.";
					}
				}
				request.setAttribute("final", out);
				view = request.getRequestDispatcher("content/homepage.jsp");
				view.include(request, response);
				break;
			case "10":
				ArrayList<L_User> list0 = librepo.findUser();
				request.setAttribute("list", list0);
				view = request.getRequestDispatcher("content/result0.jsp");
				view.forward(request, response);
				break;
			case "11":
				if (v1.equals("")) {
					out = "You have not entered enough information. Please retry.";
					request.setAttribute("final", out);
					view = request.getRequestDispatcher("content/homepage.jsp");
					view.include(request, response);
				} else {
					ArrayList<Reserve> list1 = librepo.findReservedRoomUser(Integer.parseInt(v1));
					request.setAttribute("list", list1);
					view = request.getRequestDispatcher("content/result1.jsp");
					view.forward(request, response);
				}
				break;
			case "12":
				if (v1.equals("")) {
					out = "You have not entered enough information. Please retry.";
					request.setAttribute("final", out);
					view = request.getRequestDispatcher("content/homepage.jsp");
					view.include(request, response);
				} else {
					ArrayList<Book> list2 = librepo.findBooksWithNumPages(Integer.parseInt(v1));
					request.setAttribute("list", list2);
					view = request.getRequestDispatcher("content/result2.jsp");
					view.forward(request, response);
				}
				break;
			case "13":
				if (v1.equals("") || v2.equals("")) {
					out = "You have not entered enough information. Please retry.";
					request.setAttribute("final", out);
					view = request.getRequestDispatcher("content/homepage.jsp");
					view.include(request, response);
				} else {
					ArrayList<Checkout> list3 = librepo.query5(v1, v2);
					request.setAttribute("list", list3);
					view = request.getRequestDispatcher("content/result3.jsp");
					view.forward(request, response);
				}
				break;
			case "14":
				if (v1.equals("")) {
					out = "You have not entered enough information. Please retry.";
					request.setAttribute("final", out);
					view = request.getRequestDispatcher("content/homepage.jsp");
					view.include(request, response);
				} else {
					ArrayList<Donate> list4 = librepo.query6(v1);
					request.setAttribute("list", list4);
					view = request.getRequestDispatcher("content/result4.jsp");
					view.forward(request, response);
				}
				break;
			case "15":
				if (v1.equals("")) {
					out = "You have not entered enough information. Please retry.";
					request.setAttribute("final", out);
					view = request.getRequestDispatcher("content/homepage.jsp");
					view.include(request, response);
				} else {
					ArrayList<Publish> list5 = librepo.query7(v1);
					request.setAttribute("list", list5);
					view = request.getRequestDispatcher("content/result5.jsp");
					view.forward(request, response);
				}
				break;
			case "16":
				if (v1.equals("")) {
					out = "You have not entered enough information. Please retry.";
					request.setAttribute("final", out);
					view = request.getRequestDispatcher("content/homepage.jsp");
					view.include(request, response);
				} else {
					ArrayList<Asset_AC> list6 = librepo.query8(v1);
					request.setAttribute("list", list6);
					view = request.getRequestDispatcher("content/result6.jsp");
					view.forward(request, response);
				}
				break;
			case "17":
				if (v1.equals("")) {
					out = "You have not entered enough information. Please retry.";
					request.setAttribute("final", out);
					view = request.getRequestDispatcher("content/homepage.jsp");
					view.include(request, response);
				} else {
					ArrayList<Checkin> list7 = librepo.query9(v1);
					request.setAttribute("list", list7);
					view = request.getRequestDispatcher("content/result7.jsp");
					view.forward(request, response);
				}
				break;
			default:
				out = "No option selected.";
				request.setAttribute("final", out);
				view = request.getRequestDispatcher("content/homepage.jsp");
				view.include(request, response);
				break;
			}
		}
	}
}
