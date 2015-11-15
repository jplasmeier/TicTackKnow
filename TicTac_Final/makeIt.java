package tTT;

import java.util.ArrayList;

public class makeIt {
	public int[][] perms (int n) 
	{
		int a = 0;
		int[][] arr = new int[10000][4];
		ArrayList<Integer> bob = new ArrayList<Integer>();
		for (int i = 0; i < n*n; i++)
		{
			if (i%n < n - 3)
			{
				arr[a][1] = i;
				arr[a][2] = i + 1;
				arr[a][3] = i + 2;
				arr[a][4] = i + 3;
				a++;
			}
		}
		for (int i = 0; i < n*n; i++)
		{
			if (i < n - n*3)
			{
				arr[a][1] = i;
				arr[a][2] = i + n;
				arr[a][3] = i + 2*n;
				arr[a][4] = i + 3*n;
				a++;
			}
		}
		for (int i = 0; i < n*n; i++)
		{
			if (((i +3*(n + 1))%n < 4) && ((i +3*(n + 1) < n*n)))
			{
				arr[a][1] = i;
				arr[a][2] = i + n + 1;
				arr[a][3] = i + 2*(n + 1);
				arr[a][4] = i + 3*(n + 1);
				a++;
			}
		}
		for (int i = 0; i < n*n; i++)
		{
			if ((i - 3*n >= 0) && i%n < n-3)
			{
				arr[a][1] = i;
				arr[a][2] = i - n - 1;
				arr[a][3] = i - 2*(n - 1);
				arr[a][4] = i - 3*(n - 1);
				a++;
			}
		}
	return arr;
	}
}
