Write Up
------------
In this program, I have the user create their own custom cruise. They select what port they want to start from,
what stops they want to make, and where they want their destination port to be.
They then can select from a number of ships, select packages, select type
, and then select what kind of room they want(as well as which room number). Now, onto how I used the different
design patterns. The builder pattern was a huge part of my program. The CruiseBuilder class is used to build an
itinerary after they select their ports. It builds a cruise object using their selections. The days at a port
is hard coded because in my experience, you only normally stay one day at a port, and I just selected a random
departure date for the cruise to leave. I also used the builder design to build a cruise ship. It asks them
all the specifics to help build a ship (i.e. company, ship, package, room, etc.). This leads me into my
factory pattern. I incorporated the factory pattern into the builder when they are selecting a room. When they
select their room, it uses the factory design to create a Cruise Room object using the CruiseRoomFactory class.
Now, onto the strategy pattern. I used the strategy pattern for 2 different things, the packages and the
cruise type. These interfaces define methods that are used for all the different packages and cruise types.
This allows for flexibility and encapsulation. As for the facade pattern. I incorporated the facade pattern
into my CruiseShipManager. The CruiseShipManager has incorporates all the important objects that the builders
and factories have created. I can run the whole program through a few methods in the CruiseShipManager class. It
makes it look clean and does not have all the logic used to create the cruise. The last design pattern I used
was the observer pattern. I used this with my client class. If the price of a room decreased, it would notify
the client and state that they will receive a refund of the difference. Thanks for reading, I hope this was
what you wanted. I had a lot of fun with this project and learned a lot about design patterns.
