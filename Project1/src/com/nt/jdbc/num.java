import java.util.*;
class  num
{
	static void NumToDigit(int n){
		int sum=0;
		int res=0;
		//while(n>0){
		//res=n%10;
			//sum=res+sum;
			//n=n/10;
			while(n>0||sum>9){
				if(n==0){
					n=sum;
					sum=0;
				}
					res=n%10;
			sum=res+sum;
			n=n/10;
			}
			System.out.println(sum);
		}
	//25678
	//System.out.println(n);
		//if(sum>9){
			//	NumToDigit(sum);
			//}
			//else{	
			//System.out.println(sum);
			//}
	public static void main(String[] args) 
	{
		Scanner sc= new Scanner(System.in);
		int num=sc.nextInt();
		 NumToDigit(num);

	}
}
