type file;

app (file wcoutfile) wordcount (file wcinfile,file wordcount)
{
  bash @wordcount @filename(wcinfile) stdout=@filename(wcoutfile);
}
string s[] = ["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15"];
file splitscript<"splitfile.sh">;
file wordcount<"filewordcount.sh">;
file filein <"small-dataset">;
file imfiles[];
file wcfiles[];

foreach j in[0:15]{
  file f<single_file_mapper;file=@strcat("input/x",s[j],".txt")>;
  imfiles[j] = f;
}

foreach i in[0:15]{
  file fw<single_file_mapper;file=@strcat("outwc/wc_",i,".out")>;
  fw = wordcount(imfiles[i],wordcount);
  wcfiles[i] = fw;
}
