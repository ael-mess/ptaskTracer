#include "pmutex.h"
#include "ptask.h"
#include "tstat.h"
#include <math.h>
#include <pthread.h>
#include <sched.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>


/*--------------------------------------------------------------*/
void init() {
    srand(time(NULL));
    ptask_init(SCHED_FIFO, GLOBAL, PRIO_INHERITANCE);
}


/*--------------------------------------------------------------*/
void task() {
    int i, a, b = 0;

    i = ptask_get_index();
    a = 2 + b;

    dt = ptask_get_period(i, MILLI) / 100.;

    while (1) {
        while (b < 1000000) {
            b++;
            a = 2+b;
        }
        printf("%d - %d:%d \n", i, b, a);
        ptask_wait_for_period();
    }
}

/*--------------------------------------------------------------*/
/*			MAIN process				*/
/*--------------------------------------------------------------*/

int main(void) {
   
    init();
    int i = task_create(task, 20, DREL, PRIO-i, NOW);
    if (i != -1) printf("Task %d created and activated\n", i);
    else exit(-1);
    
    int j = task_create(task, PER, DREL, PRIO-j, NOW);
    if (i != -1) printf("Task %d created and activated\n", i);
    else exit(-1);

        if ((k >= KEY_0) && (k <= KEY_9)) {
            a = 2. * G * (float)TOP;
            pthread_mutex_lock(&mxv);
            v0[k - KEY_0] = sqrt(a);
            pthread_mutex_unlock(&mxv);
        }

        if ((k == KEY_O) && (ntasks > 9)) {
            for (j = 10; j < ntasks; j++) {
                h = rand() % (TOP - BASE);
                a = 2. * G * (float)h;
                pthread_mutex_lock(&mxv);
                v0[j] = sqrt(a);
                pthread_mutex_unlock(&mxv);
            }
        }

        if (k == KEY_A) {
            for (j = 0; j < ntasks; j++) {
                h = rand() % (TOP - BASE);
                a = 2. * G * (float)h;
                pthread_mutex_lock(&mxv);
                v0[j] = sqrt(a);
                pthread_mutex_unlock(&mxv);
            }
        }

        /*
          Printing deadline misses
          TO BE DONE
          for (j=0; j<ntasks; j++) {
          sprintf(s, "%d", task_dmiss(j));
          textout_ex(screen, font, s, 50+j*48, 450, 7, 0);
          }
        */

    } while (k != KEY_ESC);

    printf("Now printing the stats\n");
    for (j = 0; j < ntasks; j++) {
        tspec wcet = ptask_get_wcet(j);
        tspec acet = ptask_get_avg(j);

        printf("TASK %d: WCET = %ld\t ACET = %ld\t NINST=%d\n", j,
               tspec_to(&wcet, MICRO), tspec_to(&acet, MICRO),
               ptask_get_numinstances(j));
    }

    printf("End of statistics\n");
    allegro_exit();
    return 0;
}

/*--------------------------------------------------------------*/
