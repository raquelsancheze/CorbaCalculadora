package CalcCliente; //


import Calculadora.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.io.*;

public class CalcCliente {

    static CalculadoraApp calcimpl;

    public static void main(String args[]) {
        try {
            double result = 0.0, num1 = 0.0, num2 = 0.0;

            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");

            NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

            String pathname = "CalculadoraApp";
            calcimpl = CalculadoraAppHelper.narrow(ncref.resolve_str(pathname));

            int ch = 1;

            while (ch != 0) {
                System.out.println("1. Soma");
                System.out.println("2. Subtração");
                System.out.println("3. Multiplicação");
                System.out.println("4. Divisão");
                System.out.println("5. Potencia");
                System.out.println("4. Raiz");
                System.out.println("0. Exit");

                BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Digite sua escolha: ");
                ch = Integer.parseInt(in1.readLine());

                if (ch == 0) {
                    break;
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Digite o primeiro valor: ");
                num1 = Double.parseDouble(in.readLine());

                System.out.println("Digite o segundo valor: ");
                num2 = Double.parseDouble(in.readLine());

                switch (ch) {
                    case 1:
                        result = calcimpl.somafn(num1, num2);
                        break;

                    case 2:
                        result = calcimpl.subtracaofn(num1, num2);
                        break;

                    case 3:
                        result = calcimpl.multiplicacaofn(num1, num2);
                        break;

                    case 4:
                        result = calcimpl.divisaofn(num1, num2);
                        break;
                    case 5:
                        result = calcimpl.potenciafn(num1, num2);
                        break;
                    case 6:
                        result = calcimpl.raizfn(num1);
                        break;
                }
                System.out.println("O resultado é: " + result);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
