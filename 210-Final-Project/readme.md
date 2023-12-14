Journal: 
Phase 0: Choosing a dataset: (Entry Date: 11/29/2023): 
	- I took a look at the Stanford Large Network Dataset Collection website that we were given, and I chose social networks as the dataset I wanted to work with for this project. I specifically chose the [ego-Facebook](https://snap.stanford.edu/data/ego-Facebook.html)  one. It's type if undirected with 4,039 nodes and 88, 234 edges. It's description  is: Social circles from Facebook(anonymized) Social networks is something I see a lot through my everyday such as on my social media, and Im genuinely curious to see what I can discover and how I can utilize that information as a programmer. Which begs the next question what type of computation can I preform on the dataset and what result will be generated from it? 
		- Well I thought of 3 options: 
		1. Community Detection: STAR
			* **Computation:** Apply community detection algorithms to identify groups or communities within the network.
			* **Result:** This will allow me to discover what groups of users interact more with each other, revealing potential subgroups/interests.
		 2. Clustering Coefficient:
			 * **Computation:** Calculate the clustering coefficient for nodes.
			 * **Result:** Understand the extent to which nodes tend to cluster together, indicating the presence of tightly-knit communities.
		 3. Graph Visualization: STAR
			 * **Computation:** Use graph visualization techniques to create a visual representation of the network.
			 * **Result:** Gain insights into the overall structure of the social network, identify patterns, and make your findings more accessible.
     	 I think I'm going to pick either community detection or Graph visualization. I believe I only need the nodeId.edges file for either of them. I also think I need the `facebook_combined.txt.gz` file, because it contains edges from all ego networks combined. However, I think I specifically want the ones only for facebook which are directed.

2.  Phase 0: Choosing a dataset: (Entry Date: 11/30/2023): 
	 * The Facebook social network requires me to learn an entire new software. So I decided to think a little smaller and not overcomplicate things and decided to create my own dataset in a cvs file.
	 * Thinking about analyzing patterns or connections between a person's major and their dorm. I think I'm going to structure it by the person's name, their major, and the dorm they live in. I think it's going to be directed.
	 * I finished writing all my thoughts/plans for my graph in data_description.txt. So I think Im done with Phase 0. Next step is 1.

3. Phase 1: Reading In: (Entry Date: 12/2/2023): 
	 * I started working on phase 1 today, which is reading in the file. I thought it was going to be a little difficult but I think I've worked with reading in a file so much throughout this semester that it didn't go too bad. I debating on reading the file in the main or the class. I decided to do it in the class because it just made more sense in an objected oriented sense. To add on, if I want to add more files or make the code reusable, and organized than I think having it in a method is better. Now moving on to the next phase. 

4. Phase 2: Computation: (Entry Date: 12/2/2023): 
	* I also started about thinking about what Computation I was going to preform to be able to analyze any patterns. I'm debating between either using Depth-first-search or Breadth-First-Search. I believe if I use DFS I can do something such as start from one major like computer science and explore all the nodes connecting to it. However, if I use BFS it will start from computer science, then check all dorms connected to it, then check the person name. I think I'm leaning it bit more towards BFS.
	* DFS: Computer science => a dorm connecting to it => then a person connecting to it. Then backtracks and goes to next dorm, then person attached to it.
	* BFS: Computer Science Node => ALL dorms connecting to it => then all people.
	* I also think my next step is to learn how to use Guava so I can implement the BFS algorithm. 

5. Phase 2: Computation: (Entry Date: 12/5/2023):  
	* I worked on the computation part again. I'm trying to use depth first search to find a way to answer any of these questions: 
		* Question: How many people CS majors live in Cushing? 
		* Question: What is the most popular house for CS Majors?
		* Question: What houses do CS mjaors live in?
	* I've implemented the dfs method but haven't really found an accurate way to answer any of my questions.

6. Phase 2: Computation: (Entry Date: 12/6/2023):  
	* Working on computation again. I think I am going to use a Hashmap to store the count because since a HashMap has key-value pair storage and retrieval I can just look up a dorm then see the counts of each major in the house. An hour later...I realized it's a bad idea the hashmap just seems to be getting bigger and bigger and becoming hard to keep track and access items. I've came to the realization that my graph is just a little too simple to implement most of the algorithms I want to use. 
		- **Outer Map Key (String):**
		    - Represents a house name.
		- **Outer Map Value (HashMap<String, Integer>):**
		    - Represents a map where:
		    - Key (String): Represents a major associated with the house.
		    - Value (Integer): Represents the count of that major in the particular house.

7. Phase 2: Computation: (Entry Date: 12/7/2023):  
	* I've decided to change my graph representation. I'm creating a MutableValueGraph. Where the nodes will be student names and the edges will have values of classes so I can see the connection of who is taking the same class with someone else. It sounds really intriguing and complex. And I am using bfs instead of dfs because I do not think I need to do a deep dive. 

 8. Phase 3: Output: (Entry Date: 12/9/2023 to 12/10/2023):  
	 * I worked on Output today. I finished my BFS which was pretty straightforward to do. However, now I decided to create an another version of the bfs method I originally created in order to get the output I wanted. The output I want to see is what students are taking the same classes together.I first talked with sky one of the TAs and she told me I could find a way to compute my results my using math. I had concatenated certain edges that had more than one class because at first the display was overwriting it for some reason. However I had just added them together, then checked to see if the value was greater than 1000. If it was I would do the reverse computation so I check them individually to see if they match to the class I'm looking for. Even though this worked it only worked for situations that only has 2 values and not more. Which is an issue because what if someone had 3 similar classes. As a result, I talked to Amanda and she helped me find a solution that works for all cases. We first started using flags to help me keep track of things but that still didn't fix the main problem. So I converted the edgeValue into a String then created a string that hold all the classes(that are now strings) and I broke the string into substrings of a specified length of 3 characters, because every class should only have 3, no more or less. Then I iterated through the result array to look at every individual class and if the class was the one I was looking for I would had it's neighbors. This worked really well and allowed me to analyze the graph to check what students where taking similar classes together.

 9. Phase 3: Output: (Entry Date: 12/11/2023):  
    I am working on trying to find a way to make my program can communicate my results in a clearer way, making sure it will work for any dataset, and the user can provide input. However, I'm experiencing some complications because gradle won't allow me to type into my termimal meaning the user can't provide any input.


Reflection: I was very detailed in my Journal entries so I'll keep my reflection a little short. I was very excited to start this project in the beginning so I started it since day one. It was a little bit scary at first having to do this independently. However, I feel like I learned a lot about how to do certain things like dfs, bfs, a little bit of Guava, and managing my time. It was definitly an experience and I had a few hiccups here and there and it did stress me out. Especially when I had to restart almost everything over. However, I decided to just focus on the good things and reminded myself that this was just apart of the learning journey. Despite that, I am happy with the way my project came out I feel like I did a great job especially on my own and I have a better understanding on how graphs are implemented not just the abstract idea of them. I also realized that even though you're allowed to change which student and class you want to analyze. You might not know all the students and classes. If you build and run the code, you will be able to see the entire graph and your options. However just in case you can't, I provided a picture for you to look at. I added a folder called pictures and it includes pictures of an example of what your output should look like so you know it's working and the options of students and classes you can choose from. The text file that you can alter and my data set is in the Data folder. Lastly, most of my code is in DataGraph.