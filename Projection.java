package VectorCompute;

public class Projection {

    public static Vector3 GetProjection(Vector3 source, Vector3 lineStart, Vector3 lineEnd){
        if (lineStart.x == lineEnd.x && lineStart.z == lineEnd.z){
            throw new IllegalArgumentException("");
        }

        /*
        * P <- source projection, A <- line start, B <- line end;
        * (AB x OY) * (AB x AP) = 0;
        * (-ABz, 0, ABx) * (ABy * APz - ABz * APy, omit, ABx * APy - ABy * APx) = 0;
        * - ABz * ABy * APz + ABz * ABz * APy + ABx * ABx * APy - ABx * ABy * APx = 0;
        * APy * (ABx * ABx + ABz * ABz) = ABz * ABy * APz + ABx * ABy * APx
        * */
        var ABx = lineEnd.x - lineStart.x;
        var ABy = lineEnd.y - lineStart.y;
        var ABz = lineEnd.z - lineStart.z;
        var APx = source.x - lineStart.x;
        var APz = source.z - lineStart.z;
        var APy = (ABz * ABy * APz + ABx * ABy * APx) / (ABx * ABx + ABz * ABz);
        return new Vector3(source.x, APy + lineStart.y, source.z);
    }
}
