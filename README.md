# streaming-media-service


TODO: 

Create FFMPEG module that will trigger MP4 to HLS format for video streaming:

ffmpeg -i file_example_MP4_1920_18MG.mp4 -profile:v baseline -level 3.0 -s 640x360 -start_number 0 -hls_time 3 -hls_list_size 0 -f hls index.m3u8
