int main()
{
	std::thread th1 (insert,10);
  	std::thread th2 (insert,50);

  	th1.join();
  	th2.join();

  	return 0;
}