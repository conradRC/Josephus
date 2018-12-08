/******************************************************************************
 *  Compilation:  javac Josephus.java
 *  Execution:    java Josephus m n
 *  Dependencies: Queue.java
 *                StDraw.java
 *
 *  % java Josephus 2 7
 *  2 4 6 1 5 3 7
 ******************************************************************************/
import java.awt.Font;
import java.lang.reflect.*;

public  class Josephus{
  static double corx[];
  static double cory[];
  public  void calcularCoordenadas(int p){   
    StdDraw.setCanvasSize(650, 650);
    Font font = new Font("Eras Light ITC", Font.BOLD, 14);
    StdDraw.setFont(font);
    StdDraw.setPenRadius(0.04);
    corx= new double [p];
    cory= new double [p];
    double x=0.5;
    double y=0.5;
    for(int c=0; c<p; c++){
      double a = (360/p*c); 
      double convert = (double) Math.toRadians(a) ;
      x = 0.43*Math.cos(convert)+0.5;
      y = 0.43*Math.sin(convert)+0.5;
      StdDraw.point(x,y);
      corx[c]=x;
      cory[c]=y;
    }
  }

  public void iniciarMatanza (int n, int m) {  
    Queue<Integer> queue = new Queue<Integer>(); 
    int numero =0;
    int count=0;
    for (int c = 1; c <= n; c++)
      queue.enqueue(c);
    
    while (!queue.isEmpty()) {
      count ++;
      for (int c = 0; c < m-1; c++)
      queue.enqueue(queue.dequeue());
      numero = queue.dequeue(); 
      if(queue.size()>0 ) { masinfo(count,m,numero); matar(numero);} 
    } 
  }
  
  private static void matar(int numero){
    Font font1 = new Font("Eras Light ITC", Font.BOLD, 14);
    StdDraw.setFont(font1);
    for(int c =1;c <=corx.length; c++){
      if(c==numero){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.point(corx[c-1],cory[c-1]);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(corx[c-1],cory[c-1],""+c); 
        StdDraw.pause(1000);
      }
    }
  } 
  
  private static void masinfo(int c,int num, int m){ 
    StdDraw.setPenColor(StdDraw.BLUE);
    if(c<25){
      StdDraw.text(c*0.04,0.985,m+"");
    }
    else if(c>=25 && c<49){
      StdDraw.text((c-24)*0.04,0.985-0.02,m+"");
    }
    else if(c>=49 && c<73){
      StdDraw.text((c-48)*0.04,0.985-0.04,m+"");
    }
    else {
      StdDraw.text((c-72)*0.04,0.985-0.06,m+"");
    }
  }
  
  
  public static void main(String[] args){
    Josephus j = new Josephus();
    int n = Integer.parseInt(args[0]); //n = vueltas
    int m = Integer.parseInt(args[1]); //m = personas
    j.calcularCoordenadas(m);
    j.iniciarMatanza(m,n);
  }
}