package onl.tesseract.creative

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.scheduler.BukkitTask
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.Trigger
import java.time.Duration
import java.time.Instant
import java.util.concurrent.Delayed
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class BukkitTaskScheduler(val plugin: Plugin, val bukkitScheduler: BukkitScheduler) : TaskScheduler {

    override fun schedule(task: Runnable, trigger: Trigger): ScheduledFuture<*>? {
        TODO("Not yet implemented")
    }

    override fun schedule(task: Runnable, startTime: Instant): ScheduledFuture<*> {
        TODO("Not yet implemented")
    }

    override fun scheduleAtFixedRate(task: Runnable, startTime: Instant, period: Duration): ScheduledFuture<*> {
        TODO("Not yet implemented")
    }

    override fun scheduleAtFixedRate(task: Runnable, period: Duration): ScheduledFuture<*> {
        val task = bukkitScheduler.runTaskTimer(plugin, task, 0L, period.seconds * 20)
        return BukkitScheduledTimerFuture(task, Instant.now(), period)
    }

    override fun scheduleWithFixedDelay(task: Runnable, startTime: Instant, delay: Duration): ScheduledFuture<*> {
        TODO("Not yet implemented")
    }

    override fun scheduleWithFixedDelay(task: Runnable, delay: Duration): ScheduledFuture<*> {
        TODO("Not yet implemented")
    }
}

class BukkitScheduledTimerFuture(private val task: BukkitTask, val startTime: Instant, val period: Duration) :
        ScheduledFuture<Unit> {

    override fun compareTo(other: Delayed?): Int {
        other ?: return 1
        return getDelay(TimeUnit.SECONDS).compareTo(other.getDelay(TimeUnit.SECONDS))
    }

    override fun getDelay(unit: TimeUnit): Long {
        val nextIn = (Instant.now().epochSecond - startTime.toEpochMilli()) % period.toMillis()
        return Duration.ofMillis(nextIn)[unit.toChronoUnit()]
    }

    override fun cancel(mayInterruptIfRunning: Boolean): Boolean {
        task.cancel()
        return true
    }

    override fun isCancelled(): Boolean {
        return task.isCancelled
    }

    override fun isDone(): Boolean {
        return false
    }

    override fun get() {
        TODO("Not yet implemented")
    }

    override fun get(timeout: Long, unit: TimeUnit) {
        TODO("Not yet implemented")
    }
}