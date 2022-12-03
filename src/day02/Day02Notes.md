# Day 2

## Task breakdown

* [ ] Calculate scores
  * [X] Scores for rock, paper, and scissors
  * [X] Scores for win, loss, tie
* [X] Compare choices
* [x] Scan input
  * Scan as lines or string?
    * Lines
  * Represent choices as Enum, Data class, something else?
    * Enum
* [ ] Interpret strategy
  * X -> lose 
  * Y -> draw 
  * Z -> win
* [ ] Enact strategy

## Flowchart

1. Get input
   1. `readInput(input)` 
2. Get choices
   1. split each line
   2. store left-hand choice in `elfOne`, right-hand in `elfTwo`
   3. translate letter to choice
3. Calculate your score for each round
4. Total your score

## Notes

Learning about `collection.zip`

* `collOne.zip(collTwo)`
* Need to have the same number of items
* Will iterate through each collection as pair of the two items at the same index
* Kind of like `collOne.forEach` but for two collections

Practicing using `Enums`

* Didn't realize at the beginning of part 2 that I'd need to expand the enums

### From JetBrains AoC Day 2 Video

* `.sumOf { }` loops (like `.forEach { }`)