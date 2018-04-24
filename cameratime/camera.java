// Camilo Gomez
// ca506812
// Computer Science 2

import java.util.*;

public class camera implements Comparable<camera>{
    int xObject;
    int yObject;
    boolean ifInRange;
    // creates new camera object to store coordinates
    public camera(int xObject, int yObject, boolean ifInRange){
        this.xObject = xObject;
        this.yObject = yObject;
        this.ifInRange = ifInRange;    
    }

    public int getxObject(){
        return xObject;
    }

    public void setx(int xObject){
        this.xObject = xObject;
    }
    @Override
    public int compareTo(camera object){
        return object.xObject - xObject;
    }
    @Override
    public String toString(){
        return xObject + "";
    }
    // Finds the slope. Y is always y = y-0; so its unnecessary
    public static double updateslope(double x, double y, double cameraPosition){
        double m;
        x = x - cameraPosition;
        m = y/x;
        return m;
    }
    // return optimimum position for the car's field of view
    public static double camPosition(double e, double d, double xObject, double yObject){
         return e - ( (d * (xObject - e) ) / (yObject - d) );
    }
    // checks to see if field of view is within glass partition
    // I had an issue comparing doubles which might be the issue with some test cases.
    public static boolean compareSlope(double cameraPosition, double xObject, 
                        double objectSlope, double bCamSlope, double eCamSlope){
        cameraPosition = (double) Math.round(cameraPosition * 10000000) / 10000000;
        xObject = (double) Math.round(xObject *               10000000) / 10000000;
        bCamSlope = (double) Math.round(bCamSlope *           10000000) / 10000000;
        eCamSlope = (double) Math.round(eCamSlope *           10000000) / 10000000;
        objectSlope = (double) Math.round(objectSlope *       10000000) / 10000000;
        Double tCam = new Double(cameraPosition);
        Double txObj = new Double(xObject);
        Double tbCam = new Double(bCamSlope);
        Double teCam = new Double(eCamSlope);
        Double tObjSlope = new Double(objectSlope);

        int compareIfPosOrNeg = tObjSlope.compareTo(0.0);
        int compareObjWithB = tObjSlope.compareTo(tbCam);
        int compareObjWithE = tObjSlope.compareTo(teCam);
        int compareXObjWithCam = txObj.compareTo(tCam);

        // this is where the problem is
        if( (compareObjWithB <= 0 && compareObjWithE >= 0) && compareIfPosOrNeg > 0)                    
           {
               // this gives you a clue on the problem
            return true;
           }
        if( (compareObjWithB <= 0 && compareObjWithE >= 0) && compareIfPosOrNeg < 0)                    
            {
            return true;
            }
        if(compareXObjWithCam == 0)
            {
            return true;
            }
        return false;
    }
    // checks if all the objects have been viewed
    public static boolean checkIfDone(camera[] allObjects){
        for(int i = 0; i < allObjects.length; i++)
        {
            if(allObjects[i].ifInRange == false)
            return false;
        }
        return true;
    }

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        
        int i;
        double lowerObjectSlope;
        double upperObjectSlope;
        double bCamSlope;
        double eCamSlope;
        int numObjects = scanner.nextInt();
        int d = scanner.nextInt();
        double b = (double)scanner.nextInt();
        double e = (double)scanner.nextInt();
        camera[] allObjects = new camera[numObjects];
        // goes through and creates objects with coordinates
        for(i = 0; i < numObjects; i++)
        {
          int xObject = scanner.nextInt();
          int yObject = scanner.nextInt();
          boolean ifInRange = false;
          allObjects[i] = new camera(xObject, yObject, ifInRange);
        }  
        // Sorts all objects in descending order
        Arrays.sort(allObjects);
        // Start camera at initial optimal position
        
        double cameraPosition = camPosition(e, d, allObjects[0].xObject, allObjects[0].yObject);
        
        int takePic = 1;
        i = 0;
        // creates the lower and upper bound of the field of view
        lowerObjectSlope = updateslope(allObjects[i].xObject, allObjects[i].yObject, cameraPosition);
        upperObjectSlope = lowerObjectSlope;
        
        int camIncrement = 0;
       
        while(camIncrement < numObjects && !checkIfDone(allObjects))
        {
            // updates the glass partition's field of view
            bCamSlope = updateslope((double)b, (double)d, cameraPosition);
            eCamSlope = updateslope((double)e, (double)d, cameraPosition);
            allObjects[i].ifInRange = compareSlope(cameraPosition, allObjects[i].xObject, upperObjectSlope, bCamSlope, eCamSlope);
          
            if(compareSlope(cameraPosition, allObjects[i].xObject, upperObjectSlope, bCamSlope, eCamSlope)
             && compareSlope(cameraPosition, allObjects[i].xObject, lowerObjectSlope, bCamSlope, eCamSlope))
            {
                allObjects[i].ifInRange = true;
                // checks if all allObjects[i] are true. returns true if they are. 
                if(checkIfDone(allObjects))
                {
                    System.out.println(takePic);
                    break;
                }
                // increment upper bound to next object.
                i++;
                upperObjectSlope = updateslope(allObjects[i].xObject, 
                allObjects[i].yObject, cameraPosition);   
                continue;
            }
                takePic++;
                
                camIncrement = i;
                // updates the camera's position
                cameraPosition = camPosition(e, d, allObjects[camIncrement].xObject, allObjects[camIncrement].yObject);
                // recalculate lower bound and upper bound
                lowerObjectSlope = updateslope(allObjects[camIncrement].xObject,allObjects[camIncrement].yObject, cameraPosition);
                
                upperObjectSlope = lowerObjectSlope;
                allObjects[camIncrement].ifInRange = compareSlope(cameraPosition, allObjects[i].xObject, upperObjectSlope, bCamSlope, eCamSlope);
        } 
    }
}
