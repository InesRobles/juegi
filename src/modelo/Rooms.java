package modelo;
import utiles.Direction;
import utiles.SoundEffects;
public class Rooms {
	private final int TILES_RES=16;
	private final int HEIGHT=11;
	private final int WIDTH=16;
	private final int SCALE=4;

	private int[][][] salas;
	private int SalaActual;
	private boolean room12Complete;
	private int room12Enemies;
	private int keysDoorRoom21;
	private SoundEffects sound;
	public Rooms() {
		sound = new SoundEffects();
		salas= new int [][][] {{
			//Sala incial
			{6,6,6,6,6,6,7,0,0,5,6,6,6,6,6,6},
			{6,6,6,6,6,0,0,0,0,0,6,6,6,6,6,7},
			{6,6,32,6,7,0,0,0,0,0,5,6,6,6,7,23},
			{6,7,0,0,0,0,0,0,0,0,0,5,6,7,23,23},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{3,4,0,0,0,0,0,0,0,0,0,2,3,4,23,23},
			{6,6,0,0,0,0,0,0,0,0,2,6,6,6,4,23},
			{6,6,3,3,3,3,4,0,0,2,6,6,6,6,6,4},
			{6,6,6,6,6,6,6,0,0,6,6,6,6,6,6,6}},{

				//Sala 1
				{7,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
				{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
				{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
				{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
				{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
				{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
				{4,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23}},
			{

					//Sala 2
					{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
					{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
					{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
					{23,0,0,23,23,23,23,0,0,23,23,23,23,0,0,23},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
					{23,0,0,23,23,23,23,0,0,23,23,23,23,0,0,23},
					{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
					{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
					{23,23,23,23,23,23,23,0,0,23,23,23,23,23,23,23}},
			{

						//Sala 3
						{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
						{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
						{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
						{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
						{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
						{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
						{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
						{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
						{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
						{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
						{23,23,23,23,23,23,23,0,0,23,23,23,23,23,23,23}},
			{
							//Sala 4
							{33,23,23,23,23,23,23,0,0,23,23,23,23,23,23,23},
							{33,33,23,23,23,23,23,0,0,23,23,23,23,23,23,23},
							{33,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
							{33,0,0,0,0,0,23,0,0,23,0,0,0,0,0,23},
							{0,0,0,23,0,0,23,0,0,23,0,0,23,0,0,23},
							{0,0,0,23,0,0,23,0,0,23,0,0,23,0,0,23},
							{0,0,0,23,0,0,23,0,0,23,0,0,23,0,0,23},
							{33,0,0,0,0,0,23,0,0,23,0,0,0,0,0,23},
							{33,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
							{33,33,23,0,23,0,23,0,0,23,0,23,0,23,23,23},
							{33,23,23,0,23,0,23,0,0,23,0,23,0,23,23,23}},	
			{
								//Sala 5
								{23,23,23,0,23,0,23,0,0,23,0,23,0,23,23,23},
								{23,23,23,0,23,0,23,0,0,23,0,23,0,23,23,23},
								{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
								{23,0,0,0,0,0,23,0,0,23,0,0,0,0,0,23},
								{0,0,0,23,0,0,0,0,0,0,0,0,23,0,0,23},
								{0,0,0,0,0,0,23,0,0,23,0,0,0,0,0,23},
								{0,0,0,23,0,0,0,0,0,0,0,0,23,0,0,23},
								{23,0,0,0,0,0,23,0,0,23,0,0,0,0,0,23},
								{23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23},
								{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
								{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23}},
			{
									//Sala 6
									{7,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
									{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
									{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
									{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
									{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
									{23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23},
									{4,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23}},
			{
										//Sala 7
										{33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33},
										{33,0,0,0,0,0,0,0,0,0,0,0,0,0,0,33},
										{33,0,0,0,0,0,0,0,0,0,0,0,0,0,0,33},
										{33,0,0,27,28,29,0,0,0,0,27,28,29,0,0,33},
										{33,0,0,30,32,31,0,0,0,0,30,34,31,0,0,0},
										{33,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										{33,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										{33,0,0,0,0,0,0,0,0,0,0,0,0,0,0,33},
										{33,0,0,0,0,0,0,0,0,0,0,0,0,0,0,33},
										{33,0,0,0,0,0,0,0,0,0,0,0,0,0,0,33},
										{33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33}},
			{

											//Sala 8
											{6,6,6,6,6,6,6,0,0,6,6,6,6,6,6,6},
											{6,6,6,6,6,6,6,0,0,6,6,6,6,6,6,7},
											{6,6,6,6,6,6,6,0,0,6,6,6,6,6,7,23},
											{6,6,6,6,6,6,7,0,0,5,6,6,6,7,23,23},
											{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
											{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
											{3,3,3,3,3,3,3,3,3,3,3,3,3,4,23,23},
											{6,6,6,6,6,6,6,6,6,6,6,6,6,6,4,23},
											{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,4},
											{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6}},
			{

												//Sala 9
												{6,6,6,6,6,6,7,0,0,5,6,6,6,6,6,6},
												{6,6,6,6,6,7,0,0,0,0,5,6,6,6,6,6},
												{6,6,6,7,0,0,0,0,0,0,0,0,5,6,6,6},
												{6,6,7,0,0,0,0,0,0,0,0,0,0,5,6,6},
												{6,6,0,0,22,0,0,0,0,0,0,22,0,0,6,6},
												{6,6,0,0,22,0,0,0,0,0,0,22,0,0,6,6},
												{6,6,0,0,22,0,0,0,0,0,0,22,0,0,6,6},
												{6,6,4,0,0,0,0,0,0,0,0,0,0,2,6,6},
												{6,6,6,4,0,0,0,0,0,0,0,0,2,6,6,6},
												{6,6,6,6,3,4,0,0,0,0,2,3,6,6,6,6},
												{6,6,6,6,6,6,4,0,0,2,6,6,6,6,6,6}},
			{

													//Sala 10
													{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
													{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
													{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
													{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
													{0,0,0,0,5,6,6,7,0,0,0,0,22,0,0,0},
													{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
													{0,0,22,0,0,0,0,0,0,0,22,0,0,0,0,0},
													{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
													{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
													{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
													{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6}},
			{
														//Sala 11
														{6,6,6,6,6,6,7,0,0,5,6,6,6,6,6,6},
														{6,6,6,6,6,7,0,0,0,0,0,5,6,6,6,6},
														{6,6,6,6,0,0,0,0,0,0,22,0,0,5,6,6},
														{6,6,6,6,0,0,0,0,0,0,0,0,0,0,5,6},
														{6,6,6,6,0,0,0,0,0,0,0,0,0,0,0,0},
														{6,6,6,7,0,0,0,0,0,0,0,0,0,0,0,0},
														{6,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0},
														{6,6,6,0,0,0,0,0,0,0,0,0,0,0,2,3},
														{6,6,6,3,4,0,0,0,0,0,22,0,0,2,6,6},
														{6,6,6,6,6,4,0,0,0,0,0,0,2,6,6,6},
														{6,6,6,6,6,6,4,0,0,2,3,3,6,6,6,6}},	
			{

															//Sala 12
															{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
															{6,6,6,7,0,0,0,0,0,0,0,0,5,6,6,6},
															{6,6,7,0,0,0,0,0,0,0,0,0,0,5,6,6},
															{6,6,0,0,0,33,0,0,0,0,33,0,0,0,6,6},
															{6,6,0,0,0,0,0,0,0,0,0,0,0,0,6,6},
															{6,6,0,0,0,0,0,0,0,0,0,0,0,0,6,6},
															{6,6,0,22,0,0,0,0,0,0,0,0,22,0,6,6},
															{6,6,0,0,0,22,0,0,0,0,22,0,0,0,6,6},
															{6,6,4,0,0,0,0,0,0,0,0,0,0,2,6,6},
															{6,6,6,3,3,3,3,3,3,3,3,3,3,6,6,6},
															{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6}},
			{

																//Sala 13
																{15,15,15,15,15,16,0,0,0,0,5,6,6,6,6,6},
																{3,3,3,3,0,0,0,0,0,0,0,0,6,6,6,6},
																{6,6,6,6,0,22,0,0,0,0,0,0,5,6,6,6},
																{6,6,6,6,0,0,0,0,0,0,0,33,0,5,6,6},
																{6,6,6,6,4,0,0,0,0,0,0,0,0,0,5,6},
																{6,6,6,6,7,0,0,0,0,0,0,0,33,0,0,6},
																{6,6,6,6,0,0,22,0,0,0,0,0,0,0,2,6},
																{6,6,6,6,0,0,22,0,0,0,0,33,0,2,6,6},
																{6,6,6,6,4,0,0,0,0,0,0,0,2,6,6,6},
																{6,6,6,6,6,4,0,0,0,2,3,3,6,6,6,6},
																{6,6,6,6,6,6,4,0,0,6,6,6,6,6,6,6}},
			{
																	//Sala 14
																	{12,12,12,12,12,13,0,0,0,0,0,0,0,11,12,12},
																	{12,12,12,12,12,16,0,0,0,0,0,0,0,11,12,12},
																	{12,12,12,15,16,0,0,0,0,0,0,0,0,14,12,12},
																	{12,12,13,0,0,0,0,0,0,0,0,0,0,0,14,15},
																	{12,12,13,0,0,0,0,0,0,0,0,0,0,0,0,0},
																	{12,12,13,0,0,0,0,0,0,0,0,0,0,0,0,0},
																	{12,12,13,0,0,0,0,0,0,0,0,0,0,0,0,0},
																	{12,12,13,0,0,0,0,0,0,0,0,0,0,2,3,3},
																	{12,12,12,10,0,0,0,0,0,0,0,0,2,6,6,6},
																	{12,12,12,12,10,0,0,0,0,0,2,3,6,6,6,6},
																	{12,12,12,12,12,10,0,0,0,0,6,6,6,6,6,6}},
			{
																		//Sala 15
																		{12,12,12,13,0,0,0,0,0,0,0,0,6,6,6,6},
																		{12,12,12,13,0,0,0,0,0,0,0,0,5,6,6,6},
																		{12,12,12,16,0,0,0,0,0,0,0,0,0,5,6,6},
																		{15,15,16,0,0,0,0,0,0,0,0,0,0,0,5,6},
																		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
																		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
																		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
																		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
																		{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
																		{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
																		{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6}},		
			{

																			//Sala 16
																			{12,12,12,12,12,12,12,12,12,12,12,12,12,13,6,6},
																			{12,12,12,12,12,12,12,12,12,12,12,12,12,13,6,6},
																			{15,15,15,15,15,15,15,12,12,12,12,12,12,16,6,6},
																			{0,0,0,0,0,0,0,14,12,12,12,12,13,2,6,6},
																			{0,0,0,0,0,0,0,0,11,12,12,12,16,6,6,6},
																			{0,0,0,0,0,0,0,0,14,15,15,16,2,6,6,6},
																			{0,0,0,0,0,0,0,0,0,0,0,0,6,6,6,6},
																			{9,9,10,0,0,0,0,0,0,0,0,0,6,6,6,6},
																			{12,12,12,10,0,0,0,0,0,0,0,0,6,6,6,6},
																			{12,12,12,13,0,0,0,0,0,0,0,0,6,6,6,6},
																			{12,12,12,13,0,0,0,0,0,0,0,0,6,6,6,6}},
			{

																				//Sala 17
																				{12,12,12,12,12,34,12,12,12,12,12,12,12,12,12,12},
																				{12,12,12,12,12,34,12,12,12,12,12,12,12,12,12,12},
																				{12,12,12,12,12,34,12,12,12,12,12,12,15,15,15,15},
																				{12,12,12,12,17,0,18,12,12,12,12,16,0,0,0,0},
																				{12,12,12,12,0,0,0,21,21,21,21,0,0,0,0,0},
																				{12,12,12,12,19,0,20,12,15,15,16,0,0,0,0,0},
																				{12,12,12,12,12,12,15,16,0,0,0,0,0,0,0,0},
																				{12,12,12,12,12,13,0,0,0,0,0,0,0,0,8,9},
																				{12,12,12,12,12,13,0,0,0,0,0,0,0,8,12,12},
																				{12,12,12,12,12,13,0,0,0,0,0,0,0,11,12,12},
																				{12,12,12,12,12,13,0,0,0,0,0,0,0,11,12,12}},
			{

																					//Sala 18
																					{12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12},
																					{12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12},
																					{12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12},
																					{12,12,12,12,12,12,12,12,12,12,17,0,0,0,18,12},
																					{12,12,12,12,17,0,18,12,12,12,0,24,0,24,0,12},
																					{12,12,12,12,0,0,0,21,21,21,0,0,0,0,0,12},
																					{12,12,12,12,19,0,20,12,12,12,0,24,0,24,0,12},
																					{12,12,12,12,12,34,12,12,12,12,19,0,0,0,20,12},
																					{12,12,12,12,12,34,12,12,12,12,12,12,12,12,12,12},
																					{12,12,12,12,12,34,12,12,12,12,12,12,12,12,12,12},
																					{12,12,12,12,12,34,12,12,12,12,12,12,12,12,12,12}},
			{
																						//Sala 19
																						{36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36},
																						{36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36},
																						{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																						{36,36,37,37,38,37,37,37,37,37,37,38,37,37,36,36},
																						{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																						{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																						{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																						{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																						{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																						{36,36,35,35,35,35,35,37,37,35,35,35,35,35,36,36},
																						{36,36,36,36,36,36,36,37,37,36,36,36,36,36,36,36}},
			{

																							//Sala 20
																							{6,6,6,6,6,6,7,0,0,5,6,6,6,6,6,6},
																							{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																							{6,0,25,0,25,0,0,0,0,0,0,25,0,25,0,6},
																							{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																							{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																							{6,0,25,0,25,0,0,0,0,0,0,25,0,25,0,6},
																							{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																							{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																							{6,0,25,0,25,0,0,0,0,0,0,25,0,25,0,6},
																							{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																							{6,3,3,3,3,3,4,0,0,2,3,3,3,3,3,6}},  
			{

																								//Sala 21
																								{6,6,6,6,6,57,40,46,46,41,54,6,6,6,6,6},
																								{6,6,6,6,6,48,58,42,43,53,50,6,6,6,6,6},
																								{6,0,0,0,0,0,24,0,0,24,0,0,0,0,0,6},
																								{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																								{6,0,0,0,0,0,24,0,0,24,0,0,0,0,0,6},
																								{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																								{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																								{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																								{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																								{6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
																								{6,3,3,3,3,3,4,0,0,2,3,3,3,3,3,6}}, 
			{

																									//Sala 22
																									{36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36},
																									{36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36},
																									{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																									{36,36,37,37,38,37,37,37,37,37,37,38,37,37,36,36},
																									{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																									{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																									{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																									{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																									{36,36,37,37,37,37,37,37,37,37,37,37,37,37,36,36},
																									{36,36,35,35,35,35,35,37,37,35,35,35,35,35,36,36},
																									{36,36,36,36,36,36,36,37,37,36,36,36,36,36,36,36}},
			{
																										//Sala 23
																										{57,47,47,47,47,47,47,47,47,47,47,47,47,47,47,54},
																										{48,58,51,51,51,51,51,51,51,51,51,51,51,51,53,50},
																										{48,64,61,39,39,39,39,39,39,39,39,39,39,62,52,50},
																										{48,64,39,39,39,39,39,39,39,39,39,39,39,39,52,50},
																										{48,64,39,39,39,39,39,39,39,39,39,39,39,39,52,50},
																										{48,64,39,39,39,39,39,39,39,39,39,39,39,39,52,50},
																										{48,64,39,39,39,39,39,39,39,39,39,39,39,39,52,50},
																										{48,64,39,39,39,39,39,39,39,39,39,39,39,39,52,50},
																										{48,64,61,39,39,39,39,39,39,39,39,39,39,62,52,50},
																										{48,55,63,63,63,63,63,67,68,63,63,63,63,63,60,50},
																										{56,49,49,49,49,49,65,71,71,66,49,49,49,49,49,59}},
		};

		SalaActual=0;
		room12Complete=false;
		room12Enemies=5;
		keysDoorRoom21=3;
	}

	public int[][][] getSalas() {
		return salas;
	}
	public int getSalaActual() {
		return SalaActual;
	}

	public boolean isRoom12Complete() {
		return room12Complete;
	}

	public void setRoom12Complete(boolean room12Complete) {
		this.room12Complete = room12Complete;
	}

	public int getRoom12Enemies() {
		return room12Enemies;
	}

	public void setRoom12Enemies(int room12Enemies) {
		this.room12Enemies -= room12Enemies;
	}

	public void setRoomValue(int sala, int x, int y, int valor) {
		salas[sala][x][y]=valor;
	}
	public void changeRoom(Player player) {
		Direction direction=player.getDirection();
		switch(SalaActual) {
		case 0:
			if(direction.equals(Direction.RIGHT)) {
				SalaActual=1;
				player.setX(player.getX());
				player.setY(0);
			}else if(direction.equals(Direction.DOWN)) {
				SalaActual=9;
				player.setX(0);
				player.setY(player.getY());
			}else if(direction.equals(Direction.LEFT)) {
				SalaActual=15;
				player.setX(player.getX());
				player.setY((WIDTH-1)*TILES_RES*SCALE);
			}else if(direction.equals(Direction.UP)) {
				SalaActual=20;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}
			break;
		case 1:
			if(direction.equals(Direction.LEFT)) {
				SalaActual=0;
				player.setX(player.getX());
				player.setY((WIDTH-1)*TILES_RES*SCALE);
			}else if(direction.equals(Direction.RIGHT)){
				SalaActual=2;
				player.setX(player.getX());
				player.setY(0);
			}
			break;
		case 2:
			if(direction.equals(Direction.LEFT)) {
				SalaActual=1;
				player.setX(player.getX());
				player.setY((WIDTH-1)*TILES_RES*SCALE);
			}else if(direction.equals(Direction.DOWN)) {
				SalaActual=4;
				player.setX(0);
				player.setY(player.getY());
			}
			break;
		case 3:
			if(direction.equals(Direction.DOWN)) {
				SalaActual=2;
				player.setX(0);
				player.setY(player.getY());
			}
			break;
		case 4:
			if(direction.equals(Direction.DOWN)) {
				SalaActual=5;
				player.setX(0);
				player.setY(player.getY());
			}else if(direction.equals(Direction.LEFT)) {
				SalaActual=7;
				player.setX(player.getX());
				player.setY((WIDTH-1)*TILES_RES*SCALE);
			}else if(direction.equals(Direction.UP)) {
				SalaActual=2;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}
			break;
		case 5:
			if(direction.equals(Direction.UP)) {
				SalaActual=4;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}else if(direction.equals(Direction.LEFT)) {
				SalaActual=6;
				player.setX(player.getX());
				player.setY((WIDTH-1)*TILES_RES*SCALE);
			}
			break;
		case 6:
			if(direction.equals(Direction.RIGHT)){
				SalaActual=5;
				player.setX(player.getX());
				player.setY(0);
			}else if(direction.equals(Direction.LEFT)) {
				SalaActual=8;
				player.setX(player.getX());
				player.setY((WIDTH-1)*TILES_RES*SCALE);
			}
			break;
		case 7:
			if(direction.equals(Direction.RIGHT)){
				SalaActual=4;
				player.setX(player.getX());
				player.setY(0);
			}
			break;
		case 8:
			if(direction.equals(Direction.RIGHT)){
				SalaActual=6;
				player.setX(player.getX());
				player.setY(0);
			} else if(direction.equals(Direction.UP)){
				SalaActual=9;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}else if(direction.equals(Direction.LEFT)) {
				SalaActual=10;
				player.setX(player.getX());
				player.setY((WIDTH-1)*TILES_RES*SCALE);
			}
			break;
		case 9:
			if(direction.equals(Direction.UP)) {
				SalaActual=0;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}else if(direction.equals(Direction.DOWN)) {
				SalaActual=8;
				player.setX(0);
				player.setY(player.getY());
			}
			break;
		case 10:
			if(direction.equals(Direction.RIGHT)){
				SalaActual=8;
				player.setX(player.getX());
				player.setY(0);
			}else if(direction.equals(Direction.LEFT)) {
				SalaActual=11;
				player.setX(player.getX());
				player.setY((WIDTH-1)*TILES_RES*SCALE);
			}
			break;
		case 11:
			if(direction.equals(Direction.RIGHT)){
				SalaActual=10;
				player.setX(player.getX());
				player.setY(0);
			}else if(direction.equals(Direction.DOWN)) {
				SalaActual=12;
				player.setX((HEIGHT-10)*TILES_RES*SCALE);
				player.setY(player.getY());
			}else if(direction.equals(Direction.UP)) {
				SalaActual=13;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}
			break;
		case 12:
			if(direction.equals(Direction.UP)) {
				SalaActual=11;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}
			break;
		case 13:
			if(direction.equals(Direction.DOWN)) {
				SalaActual=11;
				player.setX(0);
				player.setY(player.getY());
			}else if(direction.equals(Direction.UP)) {
				SalaActual=14;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}
			break;
		case 14:
			if(direction.equals(Direction.DOWN)) {
				SalaActual=13;
				player.setX(0);
				player.setY(player.getY());
			}else if(direction.equals(Direction.RIGHT)){
				SalaActual=15;
				player.setX(player.getX());
				player.setY(0);
			}else if(direction.equals(Direction.UP)) {
				SalaActual=17;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}
			break;
		case 15:
			if(direction.equals(Direction.RIGHT)){
				SalaActual=0;
				player.setX(player.getX());
				player.setY(0);
			}else if(direction.equals(Direction.LEFT)) {
				SalaActual=14;
				player.setX(player.getX());
				player.setY((WIDTH-1)*TILES_RES*SCALE);
			}else if(direction.equals(Direction.UP)) {
				SalaActual=16;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}
			break;
		case 16:
			if(direction.equals(Direction.LEFT)) {
				SalaActual=17;
				player.setX(player.getX());
				player.setY((WIDTH-1)*TILES_RES*SCALE);
			}else if(direction.equals(Direction.DOWN)) {
				SalaActual=15;
				player.setX(0);
				player.setY(player.getY());
			}
			break;
		case 17:
			if(direction.equals(Direction.DOWN)) {
				SalaActual=14;
				player.setX(0);
				player.setY(player.getY());
			}else if(direction.equals(Direction.RIGHT)){
				SalaActual=16;
				player.setX(player.getX());
				player.setY(0);
			}else if(direction.equals(Direction.UP)) {
				SalaActual=18;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}
			break;
		case 18:
			if(direction.equals(Direction.DOWN)) {
				SalaActual=17;
				player.setX(0);
				player.setY(player.getY());
			}
			break;
		case 19:
			if(direction.equals(Direction.DOWN)) {
				sound.play("/Resources/SFX/Soundeffects/Door.wav");
				SalaActual=7;
				player.setX(325);
				player.setY(255);
			}
			break;
		case 20:
			if(direction.equals(Direction.DOWN)) {
				SalaActual=0;
				player.setX(0);
				player.setY(player.getY());
			}else if(direction.equals(Direction.UP)) {
				SalaActual=21;
				player.setX((HEIGHT-1)*TILES_RES*SCALE);
				player.setY(player.getY());
			}
			break;
		case 21:
			if(direction.equals(Direction.DOWN)) {
				SalaActual=20;
				player.setX(0);
				player.setY(player.getY());
			}
			break;
		case 22:
			if(direction.equals(Direction.DOWN)) {
				SalaActual=0;
				player.setX(230);
				player.setY(160);
			}
			break;
		}

	}
	public void door(Player player) {
		switch(SalaActual) {
		case 0:
			sound.play("/Resources/SFX/Soundeffects/Door.wav");
			SalaActual=22;
			player.setX(570);
			player.setY(483);
			break;
		case 7:
			sound.play("/Resources/SFX/Soundeffects/Door.wav");
			SalaActual=19;
			player.setX(570);
			player.setY(483);
			break;
		case 21:
			sound.play("/Resources/SFX/Soundeffects/Door.wav");
			SalaActual=23;
			player.setX(505);
			player.setY(475);
			break;
		case 23:
			if(player.getDirection()==Direction.DOWN) {
				sound.play("/Resources/SFX/Soundeffects/Door.wav");
				SalaActual=21;
				player.setX(150);
				player.setY(485);
			}
		}
	}

	public void lockedDoor(Player player) {
		switch(SalaActual) {
		case 21:
			if(player.getKeys()>=1) {
				keysDoorRoom21--;
				player.useKey(1);
			}
			if(keysDoorRoom21==0) {
				salas[21][1][7]=44;
				salas[21][1][8]=45;
			}
			break;
		}
	}
}