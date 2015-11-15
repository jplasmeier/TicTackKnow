# TicTackKnow
Scalable, self-learning tic tac toe game in Java

## Inspiration
I love algorithms, and I heard of minimax from a friend at work, so I had to try it! At first i fully implemented it in flash, but then there were problems, like flash being too slow to run it efficiently. Don't get me wrong, flash is still bae. But anyways, my friend Justin was bored, and so we decided we should try implementing it in Java! I've never done Java before, or any non-actionscript non-racket language for algorithms!

## What it does
It is an AI that can play Tic Tac Toe on any size of a grid with it taking any number of moves to win. It's not a god, but it is very hard to beat on larger boards! It uses a minimax algorithm to determine the moves. It prunes the tree by searching for wins. On a seperate file, we have working machine learning. After each loss, it finds the losing move, and will never do it again!

## How I built it
We used an already existing gui, and edited to allow a 5x5 board with it requiring 4 to win. Then we developed the ai. After that we made everything algorithms, so we could scale the board and the number to win. Our AI is pretty strong the larger the board gets. The machine learning, it stores losses in files, and checks to see if it is making the same mistake.

## Challenges I ran into
It was hard to make minimax fully functional, it's quite complex. Also working in Java was difficult because I've never done that before! But we got it all to work.

## Accomplishments that I'm proud of
Getting minimax with pruning to work! Machine Learning was awesome too! Unlimited board size and winning size algorithms as well!

## What I learned
I learnt a lot of java in the process, and improved my understanding of mutual recursion, trees, and this algorithms.

## What's next for Tic Tac Toe AI LMAO
Combing into one file. Using it to test ram, and solve tic tac toe. Getting the AI to play itself and learn.
