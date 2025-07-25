package MO7;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
//import java.io.*;

//@WebServlet("/GetParameters")
public class GetParameters extends HttpServlet {

    /** Process the HTTP Post/get request */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // get the HTML form data
            double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
            double annualInterestRate = Double.parseDouble(request.getParameter("annualInterestRate"));
            int numberOfYears = Integer.parseInt(request.getParameter("numberOfYears"));

            // perform the calculation for loan payment amount
            double monthlyInterestRate = annualInterestRate / 100 / 12;
            

            double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
            double totalPayment = (monthlyPayment * numberOfYears * 12);

            // sending the resultant monthly payment back to the client
            out.println("<html>");
            out.println("<head><title>Monthly Loan Payment Result</title></head>");
            out.println("<body>");
            out.println("<p>Loan Amount: $" + String.format("%.2f", loanAmount) + "<p>");
            out.println("<p>Annual Interest Rate: " + annualInterestRate + "%</p>");
            out.println("<p>Number of Years: " + numberOfYears + "<p>");
            out.println("<p>Monthly Payment: $" + String.format("%.2f", monthlyPayment) + "<p>");
            out.println("<p>Total Payment: $" + String.format("%.2f", totalPayment));

        } catch (NumberFormatException e) {
            out.println("<html><body><p>Invalid input. Please enter a valid number.<p></body></html>");

        } catch (Exception e) {
            out.println("<html><body><p>There has been an error: " + e.getMessage() + "<p></body></html>");
        } finally {
            out.close();
        }

        
    }


}