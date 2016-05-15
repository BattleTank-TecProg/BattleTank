package Operacoes;
import java.util.logging.*;
public class Division {
		
		public static final double LARGER_SIZE_FOR_A_DOUBLE_VARIABLE = 321321.3123435;
		public static final double MINIMUM_MOST_FOR_A_DOUBLE_VARIABLE = -3123.434354;

		private String erroDividedByZero() {
			return "No is possible divided by zero";
		}
		
		public double divided(double divider, double dividend) {
			// -1 is value for initializable variable
			double quotient = -1;
			try {
				quotient = divider / dividend;
			} catch (ArithmeticException ImpossibleDividedByZero) {
				//LOG.severe(erroDividedByZero());
				divided(divider, dividend);
			}
			return quotient;
		}

		public int centreZLessBodyCenterZPlayerTank(int dividerInt, int dividendInt) {
			double dividerDouble = (double) dividerInt;
			double dividendDouble = (double) dividendInt;
			int quotientInt = (int) divided(dividerDouble, dividendDouble);
			return quotientInt;
		}

}
