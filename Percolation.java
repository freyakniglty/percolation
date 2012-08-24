public class Percolation {
   private WeightedQuickUnionUF uf;   
   //site is opened or closed
   private int [] condition;
   private int rowLength;
    // create N-by-N grid, with all sites blocked
   public Percolation(int N){ 
       rowLength=N;
       //+2 are cells on the top and on the bottom of the grid.
       uf=new WeightedQuickUnionUF(N*N+2);
       condition=new int[N*N+2];
       for (int i=1;i<=N;i++){
           condition[i]=0;
       } 
       //opens cells on the top and the bottom
       condition[N*N+1]=1;
       condition[N*N+2]=1;
       
   }
       
   // open site (row i, column j) if it is not already
   public void open(int i, int j) {  
       validate(i,j);
       if(!isOpen(i,j)){
       condition[xyTo1D(i,j)]=1; 
       //if cell opened in first row
         if(i==1){
           uf.union(rowLength*rowLength+1,xyTo1D(i,j)); 
           //if cell under is opened
           if(isOpen(i+1,j)){
               uf.union(xyTo1D(i,j),xyTo1D(i+1,j));
           }
           else {
         //if cell opened in last row
         if(i==rowLength){
             uf.union(rowLength*rowLength+2,xyTo1D(i,j));  
             //if cell over is opened
             if(isOpen(i-1,j)){
               uf.union(xyTo1D(i,j),xyTo1D(i-1,j));
           }
             else {
         //if cell in the 1-st column (not 1st and last row)
         if(j==1){
             if(isOpen(i+1,j)){
                 uf.union(xyTo1D(i,j),xyTo1D(i+1,j));
             }
             if(isOpen(i,j+1)){
                 uf.union(xyTo1D(i,j),xyTo1D(i,j+1));
             }
             if(isOpen(i-1,j)){
                  uf.union(xyTo1D(i,j),xyTo1D(i-1,j));
             }
             else {
        //if cell in the last column (not 1st and last row)
         if (j==rowLength){
             if(isOpen(i,j-1)){
                 uf.union(xyTo1D(i,j),xyTo1D(i,j-1));
             }
             if(isOpen(i+1,j)){
                 uf.union(xyTo1D(i,j),xyTo1D(i+1,j));
             }                
             if(isOpen(i-1,j)){
                  uf.union(xyTo1D(i,j),xyTo1D(i-1,j));
         }               

             else {
                 if(isOpen(i+1,j)){
                 uf.union(xyTo1D(i,j),xyTo1D(i+1,j));
             }
             if(isOpen(i,j+1)){
                 uf.union(xyTo1D(i,j),xyTo1D(i,j+1));
             }
             if(isOpen(i-1,j)){
                  uf.union(xyTo1D(i,j),xyTo1D(i-1,j));
             }
             if(isOpen(i,j-1)){
                  uf.union(xyTo1D(i,j),xyTo1D(i,j-1));
             }
             }  
         }
             }
         }
           }
         }
       }
   }
       }
   }
     
   
   // is site (row i, column j) open?
   public boolean isOpen(int i, int j) {
       return condition[xyTo1D(i,j)]==1;
   }
   
   // is site (row i, column j) full?
   public boolean isFull(int i, int j) {
       return false;          
   }  
   
   // does the system percolate?
   public boolean percolates() {
       return false;
   
   }  
   
   //Converts 2D coordinates to 1D
   private int  xyTo1D(int i, int j){
       return rowLength*(i-1)+j;
   }
   
   //validating indices
   private void validate(int i, int j){
       if(i>rowLength||j>rowLength){
           throw new IllegalArgumentException();
       }
   }
}