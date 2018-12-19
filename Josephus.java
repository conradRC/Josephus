/******************************************************************************
 *  Compilation:  javac Josephus.java
 *  Execution:    java Josephus m n
 *  Dependencies: Queue.java
 *                StDraw.java
 *  % java Josephus 2 7
 *  2 4 6 1 5 3 7
 ******************************************************************************/
import java.awt.Font;
import java.lang.reflect.*;
public  class Josephus{
  static double corx[],cory[];
  private void calcularCoordenadas(int personas){   
    corx= new double [personas];
    cory= new double [personas];
    double x,y,origen =0.5;
    double radio      =0.3;
    for(int c=0; c<personas; c++){
      double grados = (360/personas*c); 
      double convert = (double) Math.toRadians(grados) ;
      x=radio*Math.cos(convert)+origen;
      y=radio*Math.sin(convert)+origen;
      corx[c]=x;
      cory[c]=y;
    }
  }
  
  private static void dibujarPuntos(){    
    StdDraw.setPenRadius(0.04);
    for(int g=0 ; g<corx.length; g++){
      StdDraw.point(corx[g],cory[g]);  
    }
  }

  public void play (int n, int m) {  
    Queue<Integer> queue = new Queue<Integer>(); 
    int eliminado,count =0;
    //masinfo(count,m,eliminado);
    for (int c = 1; c <= n; c++){
      queue.enqueue(c);
    }
    while (!queue.isEmpty()){
      count ++;
      for (int c = 1; c < m; c++)
      queue.enqueue(queue.dequeue());
      eliminado = queue.dequeue(); 
      masinfo(count,eliminado);
      eliminar(eliminado-1);
    } 
  }
  
  private static void eliminar(int eliminado){
    Font font = new Font("Eras Light ITC", Font.BOLD, 14);
    StdDraw.setFont(font);   
    StdDraw.setPenRadius(0.05);
    
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.point(corx[eliminado],cory[eliminado]);
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.text(corx[eliminado],cory[eliminado],""+eliminado); 
    StdDraw.pause(1000);
  } 
  
  private static void masinfo(int c, int m){ 
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.text(c*0.04,0.985,m+"");
  }
  
  public static void main(String[] args){
    Josephus j = new Josephus();
    int n = 2; ///Integer.parseInt(args[0]); //n = vueltas
    int m = 10;//Integer.parseInt(args[1]); //m = personas
    j.calcularCoordenadas(m);
    j.dibujarPuntos();
    j.play(m,n);
  }
}