public class Event{

    // търсене дали на дата date в зала hall вече има събитие
    Boolean SearchEvent(String date, Integer hall)
    {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String d = parts[0];
            String event=parts[1];
            int h = Integer.parseInt(parts[2]);
            if (d.equals(date) && hall==h) return true;
        
        }
        return false;
    }

    //добавяне на събитие 
    void AddEvent(String date, Integer hall, String event)
    {
        String line=date+" "+hall.toString()+" "+event; 
        append(Constants.Events_FILE, line);
    }

}