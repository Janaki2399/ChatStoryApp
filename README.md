ChatStoryApp is an Android Application which displays captivating stories using a chat interface.

This App has the following features 

1.It contains three type of messages: sender's message (right aligned), the receiver's message (left aligned) and center aligned message. 

2. 2 type of messages are shown, Text message and Image Message  

3. A new message is be added every-time any user taps anywhere on the screen. 
4. The Messages are scrollable. 

5. On clicking on the screen, it checks if the network is available. 
    If yes,it shows the new message 
    If no, it replaces the screen with a new fragment, giving internet connectivity error, having a button of retry. 
    On Clicking retry chat screen is back and the flow shall be the same as above. 
    
6.The chat screen is replaced with a new fragment whenever internet reachability is turned off.  

7.Entire chat is not reloaded on adding the new message, just a new message is inserted in the last of the chat. 
