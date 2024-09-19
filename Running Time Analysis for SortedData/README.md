After I tested the actual running time for different size of sorting arraylist, the following is the graph I plot.
![Picture1](https://github.com/WFUCSCCho/project-4-FionaZhang23/assets/157412975/2a0ee320-2922-43e1-9ed5-f4629d9db133)
Based on this graph, I discover that the trend is mostly linear and is increasing with the size N. Because the runtime stay the nearly the same for the first 20 size, after 20, the runtime is approximately increasing linearly. However, Based on the Big-Oh analysis, since this is a bubble sort process with a do-while loop and a inner loop,
the predicted runtime formula should be O(N^2). Comparing to the actual graph, the run time should be increasing more as predicted. The graph displays a more linear growth for runtime with the growth of size N. 
