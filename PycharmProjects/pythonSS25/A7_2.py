import os
from multiprocessing import Lock, Process, Queue, current_process
import time
import queue # imported for using queue.Empty exception

#a)------------------------------------------------
def do_job(numbers_to_calculate, numbers_calculated):
    while True:
        try:
            '''try to get task from the queue. get_nowait() function will 
                raise queue.Empty exception if the queue is empty. 
                queue(False) function would do the same task also.'''
            k_task =numbers_to_calculate.get_nowait()
        except queue.Empty:
            break
        else:
            '''if no exception has been raised, add the task completion 
                message to task_that_are_done queue'''
            print(f"process {os.getpid()} is now calculating sum {k_task}")
            k = ((-1)**k_task) / (2*k_task + 1)
            numbers_calculated.put(k)
            time.sleep(1)
    return True


def multi_a():
    n = 20
    number_of_processes = 4
    sums_to_calculate = Queue() #queses die zwischen allen prozessen geteilt werden
    sums_calculated = Queue()
    processes = []

    for i in range(n):
        sums_to_calculate.put(i)

    # creating processes
    for w in range(number_of_processes):
        p = Process(target=do_job, args=(sums_to_calculate, sums_calculated))
        processes.append(p)
        p.start()

    # completing process
    for p in processes:
        p.join()

    # print the output (solange in der "fertig" queue was ist wird das ausgegeben)
    pi = 0
    while not sums_calculated.empty():
        k = sums_calculated.get()
        pi += k *4
        print("pi: ", pi)

    return True

# b)-------------------------------

def do_job_b(sums_calculated, number_each, w):
    sum = 0
    for i in range(number_each*w, number_each*(w+1)):
        sum += ((-1) ** i) / (2 * i + 1)

    print(f"process {os.getpid()} is now calculating sums from {number_each*w} to {number_each*(w+1)}")
    sums_calculated.put(sum)
    time.sleep(1)
    return True


def multi_b():
    number_each = 20
    number_of_processes = 4
    sums_calculated = Queue()
    processes = []

    # creating processes
    for w in range(number_of_processes):
        p = Process(target=do_job_b,args=(sums_calculated, number_each, w))
        processes.append(p)
        p.start()

    # completing process
    for p in processes:
        p.join()

    # print the output (solange in der "fertig" queue was ist wird das ausgegeben)
    pi = 0
    while not sums_calculated.empty():
        k = sums_calculated.get()
        pi += k *4
        print("pi: ", pi)

    return True





def main():
    multi_b()

if __name__ == '__main__':
    main()
