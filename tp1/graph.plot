set term png;
set output 'graph.png';
plot 'file.txt' using 1:2 title 'Occurences' with dots;