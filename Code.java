String swapLexOrder(String str, int[][] pairs) {
    
    if(pairs.length == 0)
        return str;
    
    List<TreeSet<Integer>> possible_set = new ArrayList<TreeSet<Integer>>();
    TreeSet<Integer> unique = new TreeSet<Integer>();
    List<Character> char_array = new ArrayList<Character>();
    
    StringBuffer string = new StringBuffer(str);
    Integer key, value;
    
    for(int i = 0; i < pairs.length; i++)
    {
        key = pairs[i][0];
        value = pairs[i][1];
        
        if(!unique.contains(key) && !unique.contains(value))
        {
            TreeSet<Integer> temp = new TreeSet<Integer>();
            temp.add(key);
            temp.add(value);
            possible_set.add(temp);
            unique.addAll(temp);
            continue;
        }
        
        if(unique.contains(key)){
            //find the set add value to that set, remove other
            //System.out.println(possible_set + " " + key + " " + value);
            possible_set = find_set(possible_set, key, value);
            unique.add(value);
        }
        
        if(unique.contains(value)){
            //System.out.println(possible_set + " " + key + " " + value);
            //find the set add key to that set, remove other
            possible_set = find_set(possible_set, value, key);
            unique.add(key);
        }

    }
    
    for(int i = 0; i < possible_set.size(); i++)
    {
        int index = 0;
        char_array.clear();
        TreeSet<Integer> temp = possible_set.get(i);
        
        for(Integer c : temp){
            char_array.add(str.charAt(c-1));
        }
        
        Collections.sort(char_array);
        
        for(Integer c : temp){
            string.setCharAt(c-1, char_array.get(char_array.size() - 1 - index++));
        }
    }
    
    System.out.println(possible_set);
    
    return string.toString();
}

List<TreeSet<Integer>> find_set(List<TreeSet<Integer>> set, Integer key, Integer value)
{
    TreeSet<Integer> temp = null;
    TreeSet<Integer> merge = null;
    int m_index = -1;
    int t_index = -1;
    
    for(int i = 0; i < set.size(); i++)
    {      
        temp = set.get(i);
        
        if(temp.contains(value))
        {
            m_index = i;
            merge = temp;
        }
        
        if(temp.contains(key))
        {
            temp.add(value);
            t_index = i;
        }

        set.set(i, temp);
    }
    
    if(m_index != -1 && t_index != -1 && m_index != t_index)
    {
        temp = set.get(t_index);
        temp.addAll(merge);
        set.set(t_index, temp);
        set.remove(m_index);
    }
    
    return set;
}
