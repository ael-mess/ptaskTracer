# Add current directory.
include_directories(.)

set(SOURCE_FILES
  pbarrier.c
  pmutex.c
  ptask.c
  ptime.c
  rtmode.c
  tstat.c
  libdl_loc.c
  dle_timer.c
  calibrate.c
)

set(HEADER_FILES
  libdl_loc.h
  pbarrier.h
  pmutex.h
  ptask.h
  ptime.h
  rtmode.h
  tstat.h
  dle_timer.h
  calibrate.h
  tp.h #mie
)

# Create a library called "ptask" which includes the source files.
add_library(ptask "STATIC" "${SOURCE_FILES}")

add_library(lttng "STATIC" "tp.c") #mie
target_compile_options(lttng "PRIVATE" "-I.") #mie

# Export.
export(TARGETS ptask FILE "./ptaskConfig.cmake")
export(PACKAGE ptask)
export(TARGETS lttng FILE "./ptaskConfig.cmake") #mie
export(PACKAGE lttng) #mie
install(FILES ${HEADER_FILES} DESTINATION ${CMAKE_CURRENT_BINARY_DIR}/include)
